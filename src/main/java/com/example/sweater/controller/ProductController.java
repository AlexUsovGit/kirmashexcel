package com.example.sweater.controller;


import com.example.sweater.domain.Filter;
import com.example.sweater.domain.NewLabel;
import com.example.sweater.domain.Product;
import com.example.sweater.domain.User;
import com.example.sweater.domain.basedictionary.Composition;
import com.example.sweater.domain.basedictionary.ProductName;
import com.example.sweater.repos.FilterRepo;
import com.example.sweater.repos.InfoClassRepo;
import com.example.sweater.repos.ProductRepo;
import com.example.sweater.repos.UserRepo;
import com.example.sweater.repos.basedictionaryrepos.CompositionRepo;
import com.example.sweater.repos.basedictionaryrepos.ProductNameRepo;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CompositionRepo compositionRepo;
    @Autowired
    private ProductNameRepo productNameRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FilterRepo filterRepo;


    @Autowired
    private InfoClassRepo infoClassRepo;


    @GetMapping("/producttable")
    public String producttable(Map<String, Object> model) {
        Iterable<Product> products = productRepo.findAllByOrderByIdDesc();
        model.put("products", products);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);

        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());

        String filterValue = "";
        model.put("filterValue", filterValue);


        return "producttable";
    }
    @PostMapping("/producttableFilter")
    public String producttableFilter(@RequestParam String barcode, Map<String, Object> model) {
        Iterable<Product> products = productRepo.findByBarcode(barcode);
        model.put("products", products);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);

        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());


        Filter filter  = new Filter(barcode);
        filterRepo.save(filter);
        String filterValue = "";

        Iterable<Filter> filters = filterRepo.findAll();
        for (Filter filter1 : filters) {
            filterValue = filter1.getValue();
        }
        model.put("filterValue", filterValue);
        return "producttable";
    }

    @GetMapping("/productProductnameAsc")
    public String producttableProductnameAsc(Map<String, Object> model) {
        Iterable<Product> products = productRepo.findAllByOrderByProductNameAsc();
        model.put("products", products);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);

        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());

        String filterValue = "";
        model.put("filterValue", filterValue);

        return "producttable";
    }

    @GetMapping("/productProductnameDesc")
    public String producttableProductnameDesc(Map<String, Object> model) {
        Iterable<Product> products = productRepo.findAllByOrderByProductNameDesc();
        model.put("products", products);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);

        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());
        String filterValue = "";
        model.put("filterValue", filterValue);
        return "producttable";
    }

    @GetMapping("/productGender")
    public String producttableGender(Map<String, Object> model) {
        Iterable<Product> products = productRepo.findAllByOrderByGenderAsc();
        model.put("products", products);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);

        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());

        String filterValue = "";
        model.put("filterValue", filterValue);
        return "producttable";

    }


    @GetMapping("/productadd")
    public String productadd(Map<String, Object> model) {
        Iterable<Product> products = productRepo.findAllByOrderByIdDesc();
        model.put("products", products);

        Iterable<Composition> compositions = compositionRepo.findAll();
        model.put("compositions", compositions);

        Iterable<ProductName> productNames = productNameRepo.findAll();
        model.put("productNames", productNames);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);
        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());
        Product product = productRepo.findFirst1ByOrderByIdDesc();
        model.put("product", product);
        return "productadd";

    }

    @PostMapping("/producttable")
    public String add(String productName, String gender, String size, String trademark,
                      String importer, String manufacturer, String article,
                      String code, String composition, String season, String barcode, String note,
                      String quantity, String dateArrive, String importPrice, String coefficient,
                      String retailPrice, String countryOfEntry, String currency, String course, Integer isDistrib,
                      Map<String, Object> model) throws ParseException {

        Product product = new Product(productName, gender, size, trademark, importer,
                manufacturer, article, code, composition, season, barcode, note,
                quantity, dateArrive, importPrice, coefficient, retailPrice, countryOfEntry, currency, course, isDistrib);

        product.setIsDistrib(0);
        productRepo.save(product);

        product.setBarcode( getBarcodesText( product.getId()));
        productRepo.save(product);


        Iterable<Product> products = productRepo.findAllByOrderByIdDesc();
        model.put("products", products);

        Iterable<Composition> compositions = compositionRepo.findAll();
        model.put("compositions", compositions);

        Iterable<ProductName> productNames = productNameRepo.findAll();
        model.put("productNames", productNames);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);

        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());

        String filterValue = "";
        model.put("filterValue", filterValue);

        return "producttable";
    }

    @PostMapping("/searchdoc")
    public String filter(@RequestParam String filter, Map<String, Object> model) throws IOException {
        Iterable<Product> products;

        if (filter != null && !filter.isEmpty()) {
            products = productRepo.findByBarcodeOrderByIdAsc(filter);

            for (Product product : products) {


                NewLabel documentPdf = new NewLabel(product);
                try {
                    documentPdf.createPdf();
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }


        } else {
            products = productRepo.findAllByOrderByIdDesc();


        }

        model.put("products", products);

        Iterable<Composition> compositions = compositionRepo.findAll();
        model.put("compositions", compositions);


        Iterable<ProductName> productNames = productNameRepo.findAll();
        model.put("productNames", productNames);


        return "documentpdf";
    }

    @PostMapping("/addToDistrib")
    public String setDistrib(@RequestParam String id, Map<String, Object> model) throws IOException {
        Iterable<Product> products;


        if (id != null && !id.isEmpty()) {
            Long longId = Long.parseLong(id);
            products = productRepo.findByIdOrderByIdAsc(longId);

            for (Product product : products) {
                product.setIsDistrib(1);

                productRepo.save(product);

            }
        }
        products = productRepo.findAllByOrderByIdDesc();

        model.put("products", products);

        Iterable<Composition> compositions = compositionRepo.findAll();
        model.put("compositions", compositions);
        Iterable<ProductName> productNames = productNameRepo.findAll();
        model.put("productNames", productNames);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);

        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());

        String filterValue = "";
        model.put("filterValue", filterValue);
        return "producttable";
    }

    @PostMapping("/deleteFromDistrib")
    public String delDistrib(@RequestParam String id, Map<String, Object> model) throws IOException {
        Iterable<Product> products;


        if (id != null && !id.isEmpty()) {
            Long longId = Long.parseLong(id);
            products = productRepo.findByIdOrderByIdAsc(longId);

            for (Product product : products) {
                product.setIsDistrib(0);

                productRepo.save(product);

            }
        }
        products = productRepo.findAllByOrderByIdDesc();

        model.put("products", products);

        Iterable<Composition> compositions = compositionRepo.findAll();
        model.put("compositions", compositions);
        Iterable<ProductName> productNames = productNameRepo.findAll();
        model.put("productNames", productNames);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);

        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());

        String filterValue = "";
        model.put("filterValue", filterValue);

        return "producttable";
    }

    @PostMapping("/documentDistribution")
    public String producttableDistributio(@RequestParam String id, Map<String, Object> model) {
        Integer intId = Integer.parseInt(id);
        Iterable<Product> products = productRepo.findByisDistrib(intId);
        model.put("products", products);


        return "documentDistribution";
    }

    private String getBarcodesText(Long id) {
        String barcodeTxt;
        String code;
        int len;
        int checksum_digit = 0;
        code = String.format("%07d", id);
        len = code.length();
        if (len == 7) {
            String[] str = code.split("");
            int sum1 = Integer.parseInt(str[1]) + Integer.parseInt(str[3]) + Integer.parseInt(str[5]);
            int sum2 = 3 * (Integer.parseInt(str[0]) + Integer.parseInt(str[2]) +
                    Integer.parseInt(str[4]) + Integer.parseInt(str[6]));
            int checksum_value = sum1 + sum2;
            checksum_digit = 10 - (checksum_value % 10);
            if (checksum_digit == 10) {
                checksum_digit = 0;
            }

            barcodeTxt = String.format("%07d", id) + String.valueOf(checksum_digit);
        } else {
            barcodeTxt = "00000000";
            System.out.println("неверный штрихкод");
        }


        return barcodeTxt;
    }
}
