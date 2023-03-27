package please.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import please.spring.dto.MemberDTO;
import please.spring.dto.ProductDTO;
import please.spring.paging.Criteria;
import please.spring.paging.PageMaker;
import please.spring.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    private final String uploadDir = "D:/spring/please-spring/src/main/resources/static/productFileUpload/";
    private String fileName1 = null;
    private String fileName2 = null;
    private String fileName3 = null;
    private String fileName4 = null;
    private String fileName5 = null;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ModelAndView index() throws Exception {
        ModelAndView mv = new ModelAndView("index");
        List<ProductDTO> selectPopulProduct = productService.selectPopulProduct();
        List<ProductDTO> selectNewProduct = productService.selectNewProduct();

        String[] categoryList = {"주문제작", "문서작성", "번역·통역", "사진·영상", "상담", "기타"};

        int[] numbers = new int[3];

        String[] category = new String[3];

        for(int insertCur = 0; insertCur < numbers.length ; insertCur++){
            numbers[insertCur] = (int)Math.round(Math.random() * (categoryList.length-1));
            for(int searchCur = 0; searchCur < insertCur; searchCur ++){
                if(numbers[insertCur] == numbers[searchCur]){
                    insertCur--;
                    break;
                }
            }
        }
        HashMap<String, Map<String,Object>> rank = new HashMap<>();

        for(int i = 0; i < numbers.length; i ++){
            category[i] = categoryList[numbers[i]];
            rank.put("category"+i, new HashMap<String, Object>());
        }

        List<ProductDTO> selectProductRank1 = productService.selectProductRank(category[0]);
        List<ProductDTO> selectProductRank2 = productService.selectProductRank(category[1]);
        List<ProductDTO> selectProductRank3 = productService.selectProductRank(category[2]);

        mv.addObject("selectPopulProduct", selectPopulProduct);
        mv.addObject("selectNewProduct", selectNewProduct);
        mv.addObject("selectProductRank1", selectProductRank1);
        mv.addObject("selectProductRank2", selectProductRank2);
        mv.addObject("selectProductRank3", selectProductRank3);

        productService.refreshReview();

        return mv;
    }

    @RequestMapping("/allProduct")
    public ModelAndView allProduct(Criteria cri) throws Exception {
        ModelAndView mv = new ModelAndView("/product/allProduct");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(productService.selectAllProductCnt());

        List<ProductDTO> selectAllProduct = productService.selectAllProduct(cri);
        mv.addObject("selectAllProduct", selectAllProduct);
        mv.addObject("pageMaker", pageMaker);

        productService.refreshReview();

        return mv;
    }

    @RequestMapping("/categoryProduct")
    public ModelAndView categoryProduct(@RequestParam("category") String category, Criteria cri, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("/product/categoryProduct");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        cri.setCategory(category);

        session.setAttribute("category", category);

        pageMaker.setTotalCount(productService.selectCategoryProductCnt(category));

        List<ProductDTO> selectCategoryProduct = productService.selectCategoryProduct(cri);

        mv.addObject("selectCategoryProduct", selectCategoryProduct);
        mv.addObject("pageMaker", pageMaker);

        productService.refreshReview();

        return mv;
    }

    @RequestMapping("/detailProduct")
    public ModelAndView detailProduct(@RequestParam("productNo") int productNo,
                                      HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("/product/detailProduct");
        ProductDTO selectDetailProduct = productService.selectDetailProduct(productNo);
        List<ProductDTO> selectReviewSimpleInfoLimit = productService.selectReviewSimpleInfoLimit(productNo);
        List<ProductDTO> selectReviewSimpleInfo = productService.selectReviewSimpleInfo(productNo);
        int selectProductOwner = productService.selectProductOwner((String) session.getAttribute("sessionUserID"), productNo);

        mv.addObject("selectDetailProduct", selectDetailProduct);
        mv.addObject("selectReviewSimpleInfoLimit", selectReviewSimpleInfoLimit);
        mv.addObject("selectReviewSimpleInfo", selectReviewSimpleInfo);

        session.setAttribute("selectProductOwner", selectProductOwner);
        session.setAttribute("sessionProductNo", productNo);

        return mv;
    }

    @GetMapping("/addProduct")
    public String addProduct() {
        return "product/addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(HttpServletRequest request, MultipartHttpServletRequest multiRequest,
                             @RequestParam Map<String, MultipartFile> file,
                             @SessionAttribute("memberDTO") MemberDTO memberDTO) throws Exception {

        ProductDTO productDTO = new ProductDTO();

        Map<String, MultipartFile> files = multiRequest.getFileMap();
        MultipartFile file1 = files.get("fileName1");
        MultipartFile file2 = files.get("fileName2");
        MultipartFile file3 = files.get("fileName3");
        MultipartFile file4 = files.get("fileName4");
        MultipartFile file5 = files.get("fileName5");

        productDTO.setFileName1(file1.getOriginalFilename());
        productDTO.setFileSize1(Integer.parseInt(String.valueOf(file1.getSize())));
        productDTO.setFileName2(file2.getOriginalFilename());
        productDTO.setFileSize2(Integer.parseInt(String.valueOf(file2.getSize())));
        productDTO.setFileName3(file3.getOriginalFilename());
        productDTO.setFileSize3(Integer.parseInt(String.valueOf(file3.getSize())));
        productDTO.setFileName4(file4.getOriginalFilename());
        productDTO.setFileSize4(Integer.parseInt(String.valueOf(file4.getSize())));
        productDTO.setFileName5(file5.getOriginalFilename());
        productDTO.setFileSize5(Integer.parseInt(String.valueOf(file5.getSize())));

        if(file1.isEmpty()) {
        } else {
            fileName1 = file1.getOriginalFilename();
            String fullPath1 = uploadDir + fileName1;
            file1.transferTo(new File(fullPath1));
        }
        if(file2.isEmpty()) {
        } else {
            fileName2 = file2.getOriginalFilename();
            String fullPath2 = uploadDir + fileName2;
            file2.transferTo(new File(fullPath2));
        }

        if(file3.isEmpty()) {
        } else {
            fileName3 = file3.getOriginalFilename();
            String fullPath3 = uploadDir + fileName3;
            file3.transferTo(new File(fullPath3));
        }

        if(file4.isEmpty()) {
        } else {
            fileName4 = file4.getOriginalFilename();
            String fullPath4 = uploadDir + fileName4;
            file4.transferTo(new File(fullPath4));
        }

        if(file5.isEmpty()) {
        } else {
            fileName5 = file5.getOriginalFilename();
            String fullPath5 = uploadDir + fileName5;
            file5.transferTo(new File(fullPath5));
        }

        productDTO.setCategory(request.getParameter("category"));
        productDTO.setProductName(request.getParameter("productName"));
        productDTO.setProductPrice(Integer.parseInt(request.getParameter("productPrice")));
        productDTO.setProductDesc(request.getParameter("productDescTxt"));
        productDTO.setSessionUserID(memberDTO.getUserID());

        productService.insertProduct(productDTO);
        return "redirect:/";
    }

    @GetMapping("/modifyProduct")
    public ModelAndView modifyProduct(HttpServletRequest request,
                                      @SessionAttribute("memberDTO") MemberDTO memberDTO,
                                      @SessionAttribute("sessionProductNo") int sessionProductNo) throws Exception {
        ModelAndView mv = new ModelAndView("product/modifyProduct");
        ProductDTO selectDetailProduct = productService.selectDetailProduct(sessionProductNo);
        mv.addObject("selectDetailProduct", selectDetailProduct);
        return mv;
    }

    @PostMapping("/modifyProduct")
    public String modifyProduct(@SessionAttribute("memberDTO") MemberDTO memberDTO, MultipartHttpServletRequest multiRequest,
                                @SessionAttribute("sessionProductNo") int sessionProductNo,
                                HttpServletRequest request,
                                @RequestParam Map<String, MultipartFile> file) throws Exception {

        ProductDTO orgProductDetail = productService.selectDetailProduct(sessionProductNo);

        String fileName1 = null;
        String fileName2 = null;
        String fileName3 = null;
        String fileName4 = null;
        String fileName5 = null;
        int fileSize1 = 0;
        int fileSize2 = 0;
        int fileSize3 = 0;
        int fileSize4 = 0;
        int fileSize5 = 0;

        Map<String, MultipartFile> files = multiRequest.getFileMap();
        MultipartFile file1 = files.get("modFileName1");
        MultipartFile file2 = files.get("modFileName2");
        MultipartFile file3 = files.get("modFileName3");
        MultipartFile file4 = files.get("modFileName4");
        MultipartFile file5 = files.get("modFileName5");

        ProductDTO productDTO = new ProductDTO();

        if(file1.isEmpty()) {
            fileName1 = orgProductDetail.getFileName1();
            fileSize1 = orgProductDetail.getFileSize1();
        } else {
            fileName1 = file1.getOriginalFilename();
            fileSize1 = Integer.parseInt(String.valueOf(file1.getSize()));
            String fullPath1 = uploadDir + fileName1;
            file1.transferTo(new File(fullPath1));
        }
        if(file2.isEmpty()) {
            fileName2 = orgProductDetail.getFileName2();
            fileSize2 = orgProductDetail.getFileSize2();
        } else {
            fileName2 = file2.getOriginalFilename();
            fileSize2 = Integer.parseInt(String.valueOf(file2.getSize()));
            String fullPath2 = uploadDir + fileName2;
            file2.transferTo(new File(fullPath2));
        }
        if(file3.isEmpty()) {
            fileName3 = orgProductDetail.getFileName3();
            fileSize3 = orgProductDetail.getFileSize3();
        } else {
            fileName3 = file3.getOriginalFilename();
            fileSize3 = Integer.parseInt(String.valueOf(file3.getSize()));
            String fullPath3 = uploadDir + fileName3;
            file3.transferTo(new File(fullPath3));
        }
        if(file4.isEmpty()) {
            fileName4 = orgProductDetail.getFileName4();
            fileSize4 = orgProductDetail.getFileSize4();
        } else {
            fileName4 = file4.getOriginalFilename();
            fileSize4 = Integer.parseInt(String.valueOf(file4.getSize()));
            String fullPath4 = uploadDir + fileName4;
            file4.transferTo(new File(fullPath4));
        }
        if(file5.isEmpty()) {
            fileName5 = orgProductDetail.getFileName5();
            fileSize5 = orgProductDetail.getFileSize5();
        } else {
            fileName5 = file5.getOriginalFilename();
            fileSize5 = Integer.parseInt(String.valueOf(file5.getSize()));
            String fullPath5 = uploadDir + fileName5;
            file5.transferTo(new File(fullPath5));
        }

        productDTO.setFileName1(fileName1);
        productDTO.setFileSize1(fileSize1);
        productDTO.setFileName2(fileName2);
        productDTO.setFileSize2(fileSize2);
        productDTO.setFileName3(fileName3);
        productDTO.setFileSize3(fileSize3);
        productDTO.setFileName4(fileName4);
        productDTO.setFileSize4(fileSize4);
        productDTO.setFileName5(fileName5);
        productDTO.setFileSize5(fileSize5);

        productDTO.setCategory(request.getParameter("modCategory"));
        productDTO.setProductName(request.getParameter("modProductName"));
        productDTO.setProductPrice(Integer.parseInt(request.getParameter("modProductPrice")));
        productDTO.setProductDesc(request.getParameter("modProductDescTxt"));
        productDTO.setSessionProductNo(sessionProductNo);
        productDTO.setSessionUserID(memberDTO.getUserID());

        productService.updateProduct(productDTO);
        return "redirect:/detailProduct?productNo="+sessionProductNo;
    }


    @RequestMapping("/deleteProduct")
    public String deleteProduct(HttpServletRequest request, @SessionAttribute("memberDTO") MemberDTO memberDTO,
                                @SessionAttribute("sessionProductNo") int sessionProductNo) throws Exception {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setSessionProductNo(sessionProductNo);
        productDTO.setSessionUserID(memberDTO.getUserID());

        productService.deleteProduct(productDTO);

        HttpSession session = request.getSession();
        session.removeAttribute("sessionProductNo");

        return "redirect:/";
    }

    @GetMapping("/addReview")
    public ModelAndView addReview(@SessionAttribute("memberDTO") MemberDTO memberDTO, int purchaseNo, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("product/addReview");
        ProductDTO selectPurchaseReview = productService.selectPurchaseReview(memberDTO.getUserID(), purchaseNo);
        mv.addObject("selectPurchaseReview", selectPurchaseReview);

        session.setAttribute("sessionProductNo", selectPurchaseReview.getProductNo());
        session.setAttribute("purchaseNo", purchaseNo);

        return mv;
    }

    @PostMapping("/addReview")
    public String addReview(HttpServletRequest request,
                            @SessionAttribute("memberDTO") MemberDTO memberDTO) throws Exception {

        HttpSession session = request.getSession();
        int sessionProductNo = (int) session.getAttribute("sessionProductNo");
        int purchaseNo = (int) session.getAttribute("purchaseNo");


        ProductDTO productDTO = new ProductDTO();

        productDTO.setUserID(memberDTO.getUserID());
        productDTO.setProductNo(sessionProductNo);
        productDTO.setReviewScore(Double.parseDouble(request.getParameter("reviewScore")));
        productDTO.setReviewDesc(request.getParameter("reviewDescTxt"));
        productDTO.setPurchaseNo(purchaseNo);

        productService.insertReview(productDTO);
        productService.updatePurchaseReview(purchaseNo);

        productService.refreshReview();

        session.removeAttribute("sessionProductNo");
        session.removeAttribute("purchaseNo");

        return "redirect:/addReviewProc";
    }

    @GetMapping("/addReviewProc")
    public String addReviewProc() {
        return "product/addReviewProc";
    }

    @GetMapping("/basketProduct")
    public ModelAndView basketProduct(@SessionAttribute("memberDTO") MemberDTO memberDTO) {
        ModelAndView mv = new ModelAndView("/product/basketProduct");
        ProductDTO selectBasketUser = productService.selectBasketUser(memberDTO.getUserID());
        List<ProductDTO> selectBasket = productService.selectBasket(memberDTO.getUserID());
        mv.addObject("selectBasketUser", selectBasketUser);
        mv.addObject("selectBasket", selectBasket);

        return mv;
    }

    @GetMapping("/addBasketProduct")
    public String addBasketProduct(@SessionAttribute("memberDTO") MemberDTO memberDTO,
                                   @SessionAttribute("sessionProductNo") int sessionProductNo) {

        productService.insertBasket(memberDTO.getUserID(),sessionProductNo);

        return "redirect:/detailProduct?productNo="+sessionProductNo;
    }

    @PostMapping("/deleteBasketItem/{basketNo}")
    public String deleteBasketItem(HttpServletRequest request,
                                   @SessionAttribute("memberDTO") MemberDTO memberDTO,
                                   @PathVariable("basketNo") int basketNo) {

        productService.deleteBasketItem(memberDTO.getUserID(), basketNo);

        return "redirect:/basketProduct";
    }

    @GetMapping("/deleteBasketAll")
    public String deleteBasketAll(@SessionAttribute("memberDTO") MemberDTO memberDTO) {

        productService.deleteBasketAll(memberDTO.getUserID());

        return "redirect:/basketProduct";
    }

    @RequestMapping("/insertPurchaseItem/{basketNo}")
    public String insertPurchaseItem(HttpServletRequest request,
                                   @SessionAttribute("memberDTO") MemberDTO memberDTO,
                                   @PathVariable("basketNo") int basketNo) {
        productService.insertPurchaseItem(memberDTO.getUserID(), basketNo);
        productService.deleteBasketItem(memberDTO.getUserID(), basketNo);

        return "redirect:/basketProduct";
    }

    @RequestMapping("/insertPurchaseBasket")
    public String insertPurchaseBasket(HttpServletRequest request,
                                       @SessionAttribute("memberDTO") MemberDTO memberDTO) {
        productService.insertPurchaseAll(memberDTO.getUserID());
        productService.deleteBasketAll(memberDTO.getUserID());

        return "redirect:/basketProduct";
    }

    @RequestMapping("/selectPurchaseList")
    public ModelAndView selectPurchaseList(@SessionAttribute("memberDTO") MemberDTO memberDTO) throws Exception {
        ModelAndView mv = new ModelAndView("product/purchaseList");
        List<ProductDTO> selectPurchaseList = productService.selectPurchaseList(memberDTO.getUserID());
        mv.addObject("selectPurchaseList", selectPurchaseList);

        return mv;
    }

}
