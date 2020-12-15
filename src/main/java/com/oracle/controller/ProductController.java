package com.oracle.controller;

import com.github.pagehelper.PageInfo;
import com.oracle.domain.Product;
import com.oracle.service.IProductService;
import com.oracle.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping("/save.do")
    public String save(Product product) throws Exception{
        productService.save(product);
        return "redirect:findAll.do";
    }
    @RequestMapping("/saveCost.do")
    public String saveCost(Product product) throws Exception{
        productService.saveCost(product);
        return "redirect:findAll.do";
    }


    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "10")Integer size)throws  Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll(page,size);
        Product product = new Product();
        int costSum = 0;
        for(int i = 0 ; i<ps.size();i++){
             product=ps.get(i);
            Date nowDate  = new Date();
            int Time = getDayDiffer(product.getInTime(),nowDate);
            System.out.println(Time);
            product.setTime(Time);
            int cost = Integer.parseInt(product.getCost());
            costSum = costSum+Time * cost;
        }
        String costSum1 = String.valueOf(costSum);
        product.setCostSum(costSum1);
        System.out.println(costSum1);
        //ps.add(product);
        PageInfo pageInfo = new PageInfo(ps);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }
    @RequestMapping("/findCar.do")
    public ModelAndView findCar(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "10")Integer size)throws  Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findCar(page,size);
        Product product = new Product();
        for(int i = 0 ; i<ps.size();i++){
            product=ps.get(i);
            int Time = getDayDiffer(product.getInTime(),product.getOutTime());
            System.out.println(Time);
            product.setTime(Time);
        }
        //ps.add(product);
        PageInfo pageInfo = new PageInfo(ps);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list1");
        return mv;
    }

    @RequestMapping("/delete.do")
    public String Delete(@RequestParam(name = "ids",required = false) String[] productIds) throws Exception{
        productService.delete(productIds);
        return "redirect:findAll.do";
    }

    @RequestMapping("/search.do")
    public ModelAndView Search(@RequestParam(name="search",required = false) String productNum) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Product> productList = productService.search(productNum);
        PageInfo<Product> pageInfo= new PageInfo(productList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }
    @RequestMapping("/search1.do")
    public ModelAndView Search1(@RequestParam(name="search",required = false) String productNum) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Product> productList = productService.search1(productNum);
        PageInfo<Product> pageInfo= new PageInfo(productList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }
    public static int getDayDiffer(Date startDate, Date endDate) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long startDateTime = dateFormat.parse(dateFormat.format(startDate)).getTime();
        long endDateTime = dateFormat.parse(dateFormat.format(endDate)).getTime();
        int i = (int) (endDateTime - startDateTime);
        System.out.println(i);
        return (int) ((endDateTime - startDateTime) / (1000 * 3600 * 24))+1;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = false) String productId) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Product> productList = productService.findById(productId);
        mv.addObject("productList",productList);
        mv.setViewName("product-edit");
        return mv;

    }
    @RequestMapping("/expurgate.do")
    public String expurgate(@RequestParam(name = "id",required = false) String productId) throws Exception{
        productService.expurgate(productId);
        return "redirect:findCar.do";
    }
    @RequestMapping("/expurgate1.do")
    public String expurgate1(@RequestParam(name = "id",required = false) String productId) throws Exception{
        productService.expurgate(productId);
        return "redirect:findAll.do";
    }

    @RequestMapping("/update.do")
    public String updateById(@RequestParam(name = "id",required = true) String productId,Product product) throws Exception{
        System.out.println(product);
        productService.updateById(productId,product);
        return "redirect:findAll.do";
    }

    @RequestMapping(value = "/exportExcel")
    public String exportExcel(HttpServletResponse response) throws Exception {
        String[] headers = {"ID","车牌", "姓名", "入库时间", "在库时长", "出库时间", "所在车库",  "车架号", "停车费", "城市","身份证号"};
        String fileName = "停车表";
        List<Product> productList = productService.findAllCar();
        Product product = new Product();
        for(int i = 0 ; i<productList.size();i++){
            product=productList.get(i);
            Date nowDate  = new Date();
            int Time = getDayDiffer(product.getInTime(),nowDate);
            System.out.println(Time);
            product.setTime(Time);
            String  cost =productService.findCost();
            int cost1 = Integer.parseInt(cost);
            int carCost = cost1 * Time;
            String carCost1 = String.valueOf(carCost);
            product.setCost(carCost1);
        }
        Map<String, Object> studentMap = new HashMap();
        studentMap.put("headers", headers);
        studentMap.put("dataList", productList);
        studentMap.put("fileName", fileName);

        List<Map> mapList = new ArrayList();
        mapList.add(studentMap);
        ExcelUtil.exportMultisheetExcel(fileName, mapList, response);
        return "success";
    }
    @RequestMapping(value = "/exportExcel1")
    public String exportExcel1(HttpServletResponse response) throws Exception {
        String[] headers = {"ID","车牌", "姓名", "入库时间", "在库时长", "出库时间", "所在车库",  "车架号", "停车费", "城市","身份证号"};
        String fileName = "停车表";
        List<Product> productList = productService.findAllCar1();
        Product product = new Product();
        for(int i = 0 ; i<productList.size();i++){
            product=productList.get(i);
            Date nowDate  = new Date();
            int Time = getDayDiffer(product.getInTime(),nowDate);
            System.out.println(Time);
            product.setTime(Time);
            String  cost =productService.findCost();
            int cost1 = Integer.parseInt(cost);
            int carCost = cost1 * Time;
            String carCost1 = String.valueOf(carCost);
            product.setCost(carCost1);
        }
        Map<String, Object> studentMap = new HashMap();
        studentMap.put("headers", headers);
        studentMap.put("dataList", productList);
        studentMap.put("fileName", fileName);

        List<Map> mapList = new ArrayList();
        mapList.add(studentMap);
        ExcelUtil.exportMultisheetExcel(fileName, mapList, response);
        return "success";
    }
    @RequestMapping(value = "/readExcel")
    public String readExcel() throws Exception {
        String filePath = "F:\\student.xls";
        List<Map<String, String>> mapList = ExcelUtil.readExcel(filePath, 0);
        return "success";
    }

}


