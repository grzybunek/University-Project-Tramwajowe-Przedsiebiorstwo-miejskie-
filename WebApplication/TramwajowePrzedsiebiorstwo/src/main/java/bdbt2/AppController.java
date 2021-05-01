package bdbt2;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private ZTMDAO ztmDao;
	@Autowired
	private PracownicyDAO pracownicyDao;
	@Autowired
	private AdresyDAO adresyDao;
	@Autowired
	private PocztyDAO pocztyDao;
	@Autowired
	private WynagrodzeniaDAO wynagrodzeniaDao;
	@Autowired
	private StanowiskaDAO stanowiskaDao;

	// Logging
	@RequestMapping("/")
	public String viewHomePage() {
		return "login1";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String checkLogin(@ModelAttribute("Login") Login login, Model model) {
		String nr_pracownika = login.getUsername();
//		String password=login.getPassword();
		login.checkPermissions();
		String permissions = login.getPermissions();
		if (permissions == null) {
			return "login1";
		}
		if (permissions.equals("a")) {
			return "redirect:/tables";
		}

		if (permissions.equals("u")) {
			// return "/users/"+nr_pracownika;
			int i = Integer.parseInt(nr_pracownika);
			try {
				Pracownicy pracownicy = pracownicyDao.get(i);
				return "redirect:/users/" + pracownicy.getNr_pracownika();

			} catch (Exception e) {
				return "login1";
			}
		} else {
			return "login1";
		}
	}

	// Administrator
	@RequestMapping("/tables")
	public String viewTables() {
		return "tables";
	}

	// adding new account
	@RequestMapping("/edit_acc")
	public String edit_acc() {
		return "edit_acc";
	}

	@RequestMapping(value = "/edit_acc/save", method = RequestMethod.POST)
	public String saveAccount(@ModelAttribute("Login") Login login) {
		login.saveToFile();
		return "redirect:/tables";
	}

	// hiring new employee
	@RequestMapping("/hire_employee")
	public String hire_employee(Model model) {
		Pracownicy pracownicy = new Pracownicy();
		Adresy adresy = new Adresy();
		Poczty poczty = new Poczty();
		Stanowiska stanowiska = new Stanowiska();
		model.addAttribute("Pracownicy", pracownicy);
		model.addAttribute("Adresy", adresy);
		model.addAttribute("Poczty", poczty);
		model.addAttribute("Stanowiska", stanowiska);

		List<ZTM> listZTM = ztmDao.list();
		model.addAttribute("listZTM", listZTM);

		List<Stanowiska> listStanowiska = stanowiskaDao.list();
		model.addAttribute("listStanowiska", listStanowiska);

		List<Poczty> listPoczty = pocztyDao.list();
		model.addAttribute("listPoczty", listPoczty);

		return "hire_employee";
	}

	@RequestMapping(value = "/hire_employee/save", method = RequestMethod.POST)
	public String save_employee(@ModelAttribute("Pracownicy") Pracownicy pracownicy,
			@ModelAttribute("Adresy") Adresy adresy, @ModelAttribute("Poczty") Poczty poczty) {
		int nrPoczty = 0;
		if (pocztyDao.isUnique2(poczty.getKod_poczty())) {
			nrPoczty = pocztyDao.saveWithNumber(poczty).intValue();
		} else {
			nrPoczty = pocztyDao.getFromKod(poczty.getKod_poczty());
		}

		adresy.setNr_poczty(nrPoczty);

		int nrAdresu = adresyDao.saveWithNumber(adresy).intValue();
		pracownicy.setNr_adresu(nrAdresu);

		String newId = pracownicyDao.saveWithNumber(pracownicy).toString();
		Properties prop = LoginProperties.load();
		LoginProperties.write(prop, newId, "123", "u");
		return "redirect:/tables";
	}

	// ZTM
	@RequestMapping("/ZTM")
	public String viewHomePageZTM(Model model) {
		List<ZTM> listZTM = ztmDao.list();
		model.addAttribute("listZTM", listZTM);
		return "index_ZTM";
	}

	@RequestMapping("/ZTM/new_ZTM")
	public String showNewFormZTM(Model model) {

		ZTM ztm = new ZTM();
		model.addAttribute("ZTM", ztm);
		return "new_form_ZTM";
	}

	@RequestMapping(value = "/ZTM/save_ZTM", method = RequestMethod.POST)
	public String saveZTM(@ModelAttribute("ZTM") ZTM ztm, Model model) {
		try {
			ztmDao.save(ztm);
		} catch (Exception e) {

			model.addAttribute("errorTekst", e.getMessage());
			model.addAttribute("error", true);
			return ("new_form_ZTM");
		}

		return "redirect:/ZTM";
	}

	@RequestMapping("/ZTM/edit_ZTM/{Nr_biura}")
	public ModelAndView showEditFormZTM(@PathVariable(name = "Nr_biura") int Nr_biura) {
		ModelAndView mav = new ModelAndView("edit_form_ZTM");
		ZTM ztm = ztmDao.get(Nr_biura);
		mav.addObject("ZTM", ztm);
		return mav;
	}

	@RequestMapping(value = "/ZTM/update_ZTM", method = RequestMethod.POST)
	public String updateZTM(@ModelAttribute("ZTM") ZTM ztm, Model model) {
		try {
			ztmDao.update(ztm);
		} catch (Exception e) {

			model.addAttribute("errorTekst", e.getMessage());
			model.addAttribute("error", true);
			return ("edit_form_ZTM");
		}
		return "redirect:/ZTM";

	}

	@RequestMapping("/ZTM/delete_ZTM/{Nr_biura}")
	public String deleteZTM(@PathVariable(name = "Nr_biura") int Nr_biura, Model model) {
		try {
			ztmDao.delete(Nr_biura);
		} catch (Exception e) {
			List<ZTM> listZTM = ztmDao.list();
			model.addAttribute("listZTM", listZTM);
			model.addAttribute("error", true);
			return ("index_ZTM");

		}
		return "redirect:/ZTM";
	}

	// Pracownicy

	@RequestMapping("/Pracownicy")
	public String viewPagePracownicy(Model model) {
		List<Pracownicy> listPracownicy = pracownicyDao.list();
		model.addAttribute("listPracownicy", listPracownicy);
		return "index_Pracownicy";
	}

	@RequestMapping("/Pracownicy/new_Pracownicy")
	public String showNewFormPracownicy(Model model) {

		Pracownicy pracownicy = new Pracownicy();
		model.addAttribute("Pracownicy", pracownicy);
		return "new_form_Pracownicy";
	}

	@RequestMapping(value = "/Pracownicy/save_Pracownicy", method = RequestMethod.POST)
	public String savePracownicy(@ModelAttribute("Pracownicy") Pracownicy pracownicy, Model model) {

		try {
			String newId = pracownicyDao.saveWithNumber(pracownicy).toString();
			Properties prop = LoginProperties.load();
			LoginProperties.write(prop, newId, "123", "u");
			return "redirect:/Pracownicy";
		} catch (Exception e) {
			model.addAttribute("errorTekst", e.getMessage());
			model.addAttribute("error", true);
			return ("new_form_Pracownicy");
		}
	}

	@RequestMapping("/Pracownicy/edit_Pracownicy/{nr_pracownika}")
	public ModelAndView showEditFormPracownicy(@PathVariable(name = "nr_pracownika") int nr_pracownika) {
		ModelAndView mav = new ModelAndView("edit_form_Pracownicy");
		Pracownicy pracownicy = pracownicyDao.get(nr_pracownika);
		mav.addObject("Pracownicy", pracownicy);
		return mav;
	}

	@RequestMapping(value = "/Pracownicy/update_Pracownicy", method = RequestMethod.POST)
	public String updatePracownicy(@ModelAttribute("Pracownicy") Pracownicy pracownicy, Model model) {
		try {
			pracownicyDao.update(pracownicy);
		} catch (Exception e) {

			model.addAttribute("errorTekst", e.getMessage());
			model.addAttribute("error", true);
			return ("edit_form_Pracownicy");
		}
		return "redirect:/Pracownicy";

	}

	@RequestMapping("/Pracownicy/delete_Pracownicy/{nr_pracownika}")
	public String deletePracownicy(@PathVariable(name = "nr_pracownika") int nr_pracownika, Model model) {
		try {
			pracownicyDao.delete(nr_pracownika);
		} catch (Exception e) {
			List<Pracownicy> listPracownicy = pracownicyDao.list();
			model.addAttribute("listPracownicy", listPracownicy);
			model.addAttribute("error", true);
			return ("index_Pracownicy");

		}
		return "redirect:/Pracownicy";
	}

	// Adresy
	@RequestMapping("/Adresy")
	public String viewHomePageAdresy(Model model) {
		List<Adresy> listAdresy = adresyDao.list();
		model.addAttribute("listAdresy", listAdresy);
		return "index_Adresy";
	}

	@RequestMapping("/Adresy/new_Adresy")
	public String showNewFormAdresy(Model model) {

		Adresy adresy = new Adresy();
		model.addAttribute("Adresy", adresy);
		return "new_form_Adresy";
	}

	@RequestMapping(value = "/Adresy/save_Adresy", method = RequestMethod.POST)
	public String saveAdresy(@ModelAttribute("Adresy") Adresy adresy, Model model) {
		try {
			adresyDao.save(adresy);
		} catch (Exception e) {
			model.addAttribute("errorTekst", e.getMessage());
			model.addAttribute("error", true);
			return ("new_form_Adresy");
		}
		return "redirect:/Adresy";
	}

	@RequestMapping("/Adresy/edit_Adresy/{nr_adresu}")
	public ModelAndView showEditFormAdresy(@PathVariable(name = "nr_adresu") int nr_adresu) {
		ModelAndView mav = new ModelAndView("edit_form_Adresy");
		Adresy adresy = adresyDao.get(nr_adresu);
		mav.addObject("Adresy", adresy);
		return mav;
	}

	@RequestMapping(value = "/Adresy/update_Adresy", method = RequestMethod.POST)
	public String updateAdresy(@ModelAttribute("Adresy") Adresy adresy, Model model) {
		try {
			adresyDao.update(adresy);
		} catch (Exception e) {

			model.addAttribute("errorTekst", e.getMessage());
			model.addAttribute("error", true);
			return ("edit_form_Adresy");
		}
		return "redirect:/Adresy";
	}

	@RequestMapping("/Adresy/delete_Adresy/{nr_adresu}")
	public String deleteAdresy(@PathVariable(name = "nr_adresu") int nr_adresu, Model model) {

		try {
			adresyDao.delete(nr_adresu);
		} catch (Exception e) {
			List<Adresy> listAdresy = adresyDao.list();
			model.addAttribute("listAdresy", listAdresy);
			model.addAttribute("error", true);
			return ("index_Adresy");
		}
		return "redirect:/Adresy";
	}

	// Poczty
	@RequestMapping("/Poczty")
	public String viewHomePagePoczty(Model model) {
		List<Poczty> listPoczty = pocztyDao.list();
		model.addAttribute("listPoczty", listPoczty);
		return "index_Poczty";
	}

	@RequestMapping("/Poczty/new_Poczty")
	public String showNewFormPoczty(Model model) {

		Poczty poczty = new Poczty();
		model.addAttribute("Poczty", poczty);
		return "new_form_Poczty";
	}

	@RequestMapping(value = "/Poczty/save_Poczty", method = RequestMethod.POST)
	public String savePoczty(@ModelAttribute("Poczty") Poczty poczty, Model model) {
		if (pocztyDao.isUnique(poczty.getKod_poczty(), poczty.getNr_poczty())) {
			pocztyDao.save(poczty);
		} else {
			model.addAttribute("Poczty", poczty);
			model.addAttribute("error", true);
			return ("new_form_Poczty");
		}

		return "redirect:/Poczty";
	}

	@RequestMapping("/Poczty/edit_Poczty/{nr_poczty}")
	public ModelAndView showEditFormPoczty(@PathVariable(name = "nr_poczty") int nr_poczty) {
		ModelAndView mav = new ModelAndView("edit_form_Poczty");
		Poczty poczty = pocztyDao.get(nr_poczty);
		mav.addObject("Poczty", poczty);
		mav.addObject("pocztyDao", pocztyDao);
		return mav;
	}

	@RequestMapping(value = "/Poczty/update_Poczty", method = RequestMethod.POST)
	public String updatePoczty(@ModelAttribute("Poczty") Poczty poczty, Model model) {
		if (pocztyDao.isUnique(poczty.getKod_poczty(), poczty.getNr_poczty())) {

			pocztyDao.update(poczty);
			return "redirect:/Poczty";
		} else {

//			String nr = String.valueOf(poczty.getNr_poczty());
			model.addAttribute("error", true);
			model.addAttribute("Poczty", poczty);
			return "edit_form_Poczty";

		}

	}

	@RequestMapping("/Poczty/delete_Poczty/{nr_poczty}")
	public String deletePoczty(@PathVariable(name = "nr_poczty") int nr_poczty, Model model) {

		try {
			pocztyDao.delete(nr_poczty);
		} catch (Exception e) {
			List<Poczty> listPoczty = pocztyDao.list();
			model.addAttribute("listPoczty", listPoczty);
			model.addAttribute("error", true);
			return ("index_Poczty");
		}
		return "redirect:/Poczty";
	}

	// Wynagrodzenia
	@RequestMapping("/Wynagrodzenia")
	public String viewHomePageWynagrodzenia(Model model) {
		List<Wynagrodzenia> listWynagrodzenia = wynagrodzeniaDao.list();
		model.addAttribute("listWynagrodzenia", listWynagrodzenia);
		return "index_Wynagrodzenia";
	}

	@RequestMapping("/Wynagrodzenia/new_Wynagrodzenia")
	public String showNewFormWynagrodzenia(Model model) {

		Wynagrodzenia wynagrodzenia = new Wynagrodzenia();
		model.addAttribute("Wynagrodzenia", wynagrodzenia);
		return "new_form_Wynagrodzenia";
	}

	@RequestMapping(value = "/Wynagrodzenia/save_Wynagrodzenia", method = RequestMethod.POST)
	public String saveWynagrodzenia(@ModelAttribute("Wynagrodzenia") Wynagrodzenia wynagrodzenia, Model model) {
		try {
			wynagrodzeniaDao.save(wynagrodzenia);
		} catch (Exception e) {
			model.addAttribute("errorTekst", e.getMessage());
			model.addAttribute("error", true);
			return ("new_form_Wynagrodzenia");
		}
		return "redirect:/Wynagrodzenia";
	}

	@RequestMapping("/Wynagrodzenia/edit_Wynagrodzenia/{nr_wynagrodzenia}")
	public ModelAndView showEditFormWynagrodzenia(@PathVariable(name = "nr_wynagrodzenia") int nr_wynagrodzenia) {
		ModelAndView mav = new ModelAndView("edit_form_Wynagrodzenia");
		Wynagrodzenia wynagrodzenia = wynagrodzeniaDao.get(nr_wynagrodzenia);
		mav.addObject("Wynagrodzenia", wynagrodzenia);
		return mav;
	}

	@RequestMapping(value = "/Wynagrodzenia/update_Wynagrodzenia", method = RequestMethod.POST)
	public String updateWynagrodzenia(@ModelAttribute("Wynagrodzenia") Wynagrodzenia wynagrodzenia, Model model) {
		try {
			wynagrodzeniaDao.update(wynagrodzenia);
		} catch (Exception e) {

			model.addAttribute("errorTekst", e.getMessage());
			model.addAttribute("error", true);
			return ("edit_form_Wynagrodzenia");

		}
		return "redirect:/Wynagrodzenia";
	}

	@RequestMapping("/Wynagrodzenia/delete_Wynagrodzenia/{nr_wynagrodzenia}")
	public String deleteWynagrodzenia(@PathVariable(name = "nr_wynagrodzenia") int nr_wynagrodzenia) {
		wynagrodzeniaDao.delete(nr_wynagrodzenia);
		return "redirect:/Wynagrodzenia";
	}

	// Stanowiska
	@RequestMapping("/Stanowiska")
	public String viewHomePageStanowiska(Model model) {
		List<Stanowiska> listStanowiska = stanowiskaDao.list();
		model.addAttribute("listStanowiska", listStanowiska);
		return "index_Stanowiska";
	}

	@RequestMapping("/Stanowiska/new_Stanowiska")
	public String showNewFormStanowiska(Model model) {

		Stanowiska stanowiska = new Stanowiska();
		model.addAttribute("Stanowiska", stanowiska);
		return "new_form_Stanowiska";
	}

	@RequestMapping(value = "/Stanowiska/save_Stanowiska", method = RequestMethod.POST)
	public String saveStanowiska(@ModelAttribute("Stanowiska") Stanowiska stanowiska) {

		stanowiskaDao.save(stanowiska);
		return "redirect:/Stanowiska";
	}

	@RequestMapping("/Stanowiska/edit_Stanowiska/{nr_stanowiska}")
	public ModelAndView showEditFormStanowiska(@PathVariable(name = "nr_stanowiska") int nr_stanowiska) {
		ModelAndView mav = new ModelAndView("edit_form_Stanowiska");
		Stanowiska stanowiska = stanowiskaDao.get(nr_stanowiska);
		mav.addObject("Stanowiska", stanowiska);
		return mav;
	}

	@RequestMapping(value = "/Stanowiska/update_Stanowiska", method = RequestMethod.POST)
	public String updateStanowiska(@ModelAttribute("Stanowiska") Stanowiska stanowiska) {

		stanowiskaDao.update(stanowiska);
		return "redirect:/Stanowiska";
	}

	@RequestMapping("/Stanowiska/delete_Stanowiska/{nr_stanowiska}")
	public String deleteStanowiska(@PathVariable(name = "nr_stanowiska") int nr_stanowiska, Model model) {

		try {
			stanowiskaDao.delete(nr_stanowiska);
		} catch (Exception e) {
			List<Stanowiska> listStanowiska = stanowiskaDao.list();
			model.addAttribute("listStanowiska", listStanowiska);
			model.addAttribute("error", true);
			return ("index_Stanowiska");
		}
		return "redirect:/Stanowiska";
	}

	// Users

	@RequestMapping("/users/{nr_pracownika}")
	public ModelAndView showUser(@PathVariable(name = "nr_pracownika") int nr_pracownika) {
		ModelAndView mav = new ModelAndView("user");
		Pracownicy pracownicy = pracownicyDao.get(nr_pracownika);
		mav.addObject("Pracownicy", pracownicy);
		Stanowiska stanowiska = stanowiskaDao.get(pracownicy.getNr_stanowiska());
		mav.addObject("Stanowiska", stanowiska);
		List<Pracownicy> listPracownicy = pracownicyDao.listOfCoworker(pracownicy.getNr_stanowiska(),
				pracownicy.getNr_pracownika());
		mav.addObject("listPracownicy", listPracownicy);
		List<Wynagrodzenia> listWynagrodzenia = wynagrodzeniaDao.listOfMoney(pracownicy.getNr_pracownika());
		mav.addObject("listWynagrodzenia", listWynagrodzenia);
		return mav;

	}

	@RequestMapping("/users/{nr_pracownika}/edit")
	public ModelAndView editUser(@PathVariable(name = "nr_pracownika") int nr_pracownika) {
		ModelAndView mav = new ModelAndView("edit_form_user");
		Pracownicy pracownicy = pracownicyDao.get(nr_pracownika);
		mav.addObject("Pracownicy", pracownicy);
		Adresy adresy = adresyDao.get(pracownicy.getNr_adresu());
		mav.addObject("Adresy", adresy);
		Poczty poczty = pocztyDao.get(adresy.getNr_poczty());
		mav.addObject("Poczty", poczty);
		return mav;

	}

	@RequestMapping(value = "/users/update_user", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("Pracownicy") Pracownicy pracownicy,
			@ModelAttribute("Adresy") Adresy adresy, @ModelAttribute("Poczty") Poczty poczty, Model model) {

		Pracownicy prac = pracownicyDao.get(pracownicy.getNr_pracownika());
		Adresy adres = adresyDao.get(prac.getNr_adresu());
		Poczty poczta = pocztyDao.get(adres.getNr_poczty());

		poczty.setNr_poczty(poczta.getNr_poczty());
		if (pocztyDao.isUnique(poczty.getKod_poczty(), poczty.getNr_poczty())) {
			pocztyDao.update(poczty);
		} else {
			int nrPoczty = pocztyDao.getFromKod(poczty.getKod_poczty());
			
			poczty.setKod_poczty(pocztyDao.get(nrPoczty).getKod_poczty());
			poczty.setNr_poczty(pocztyDao.get(nrPoczty).getNr_poczty());
			poczty.setPoczta(pocztyDao.get(nrPoczty).getPoczta());
			pocztyDao.update(poczty);
		}
		adresy.setNr_adresu(adres.getNr_adresu());
		adresy.setNr_poczty(poczty.getNr_poczty());
		adresyDao.update(adresy);

		pracownicy.setData_zatrudnienia(prac.getData_zatrudnienia());
		pracownicy.setNr_stanowiska(prac.getNr_stanowiska());
		pracownicy.setNr_biura(prac.getNr_biura());
		pracownicy.setNr_adresu(prac.getNr_adresu());
		pracownicyDao.update(pracownicy);

		return "redirect:/users/" + pracownicy.getNr_pracownika();

	}

//	@RequestMapping("/error")
//	public String errorPage() {
//		
//		return "error";
//	}

}
