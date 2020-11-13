package com.example.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.ShipmentType;
import com.example.service.IShipmentTypeService;
import com.example.util.ShipmentTypeUtil;
import com.example.view.ShipmentTypeExcelView;
import com.example.view.ShipmentTypePdfView;

@Controller
@RequestMapping("/Shipment")
public class ShipmentTypeController {
	@Autowired
	private IShipmentTypeService service;
	
	@Autowired
	private ShipmentTypeUtil util;
	
	@Autowired
	private ServletContext context;
	
	//1. show Register page
	@GetMapping("/register")
	public String showReg(Model model) {
		//Form Backing Object
		model.addAttribute("shipmentType", new ShipmentType());
		return "ShipmentTypeRegister";
	}
	
	//2. save
	@PostMapping("/save")
	public String save(
			@ModelAttribute ShipmentType shipmentType,
			Model model) 
	{
		Integer id = service.saveShipmentType(shipmentType);
		String message = "ShipmentType '"+id+"' Saved";
		model.addAttribute("message", message);
		//Form Backing Object
		model.addAttribute("shipmentType", new ShipmentType());
		return "ShipmentTypeRegister";
	}
	
	//3. display all
	@GetMapping("/all")
	public String showAll(Model model) {
		model.addAttribute("list", service.getAllShipmentTypes());
		return "ShipmentTypeData";
	}
	
	//4. delete by id
	@GetMapping("/delete")
	public String deleteOne(
			@RequestParam Integer id, 
			Model model)
	{
		service.deleteShipmentType(id);
		model.addAttribute("message", "ShipmentType '"+id+"' deleted");
		model.addAttribute("list", service.getAllShipmentTypes());
		return "ShipmentTypeData";
	}
	
	//5. show edit
	@GetMapping("/edit")
	public String showEdit(
			@RequestParam Integer id,
			Model model)
	{
		model.addAttribute("shipmentType", service.getOneShipmentType(id));
		return "ShipmentTypeEdit";
	}
	
	//6. update
	@PostMapping("/update")
	public String doUpdate(
			@ModelAttribute ShipmentType shipmentType,
			Model model) 
	{
		service.updateShipmentType(shipmentType);
		model.addAttribute("message", "ShipmentType '"+shipmentType.getId()+"' Updated");
		model.addAttribute("list", service.getAllShipmentTypes());
		return "ShipmentTypeData";	
	}
	
	//7. Excel Export
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m = new ModelAndView();
		
		//service call
		List<ShipmentType> list = service.getAllShipmentTypes();
		m.addObject("list", list);

		if(list==null || list.isEmpty()) {
			m.addObject("message", "NO DATA FOR EXCEL EXPORT");
			m.setViewName("ShipmentTypeData");
		} else { //data exist then export
			m.setView(new ShipmentTypeExcelView());
		}
		
		return m;
	}
	
	//8. Export data to PDF
	@GetMapping("/pdf")
	public ModelAndView exportToPdf() {
		ModelAndView m = new ModelAndView();
		
		//service call
		List<ShipmentType> list = service.getAllShipmentTypes();
		m.addObject("list", list);

		if(list==null || list.isEmpty()) {
			m.addObject("message", "NO DATA FOR EXCEL EXPORT");
			m.setViewName("ShipmentTypeData");
		} else { //data exist then export
			m.setView(new ShipmentTypePdfView());
		}
		return m;
	}
	
	//9. validate code using AJAX
	@GetMapping("/validate")
	public @ResponseBody String validateCode(
			@RequestParam String code) 
	{
		String message = "";
		if(service.isShipmentTypeCodeExist(code)) {
			message = "Shipment Code '"+code+"' already exist";
		}
		return message;
	}
	
	//10. charts
	@GetMapping("/charts")
	public String generateCharts() {
		List<Object[]> data = service.getShipmentModeAndCount();
		String path = context.getRealPath("/");
		//System.out.println(path);
		util.generateBar(path, data);
		util.generatePie(path, data);
		return "ShipmentTypeCharts";
	}
	
	
}