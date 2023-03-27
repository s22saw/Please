package please.spring.service;

import please.spring.dto.ProductDTO;
import please.spring.paging.Criteria;

import java.util.List;

public interface ProductService {
    List<ProductDTO> selectPopulProduct() throws Exception;
    List<ProductDTO> selectNewProduct() throws Exception;
    List<ProductDTO> selectAllProduct(Criteria cri) throws Exception;
    List<ProductDTO> selectCategoryProduct(Criteria cri) throws Exception;
    int selectAllProductCnt() throws Exception;
    int selectCategoryProductCnt(String Category) throws Exception;
    List<ProductDTO> selectProductRank(String Category) throws Exception;
    ProductDTO selectDetailProduct(int productNo) throws Exception;
    List<ProductDTO> selectReviewSimpleInfoLimit(int productNo) throws Exception;
    List<ProductDTO> selectReviewSimpleInfo(int productNo) throws Exception;

    void insertProduct(ProductDTO productDTO) throws Exception;
    void updateProduct(ProductDTO productDTO) throws Exception;
    void deleteProduct(ProductDTO productDTO) throws Exception;

    int selectProductOwner(String sessionUserID, int sessionProductNo) throws Exception;

    ProductDTO selectPurchaseReview(String sessionUserID, int purchaseNo);
    void updatePurchaseReview(int purchaseNo);
    void insertReview(ProductDTO productDTO) throws Exception;
    void refreshReview();

    ProductDTO selectBasketUser(String sessionUserID);
    List<ProductDTO> selectBasket(String sessionUserID);
    void insertBasket(String sessionUserID, int sessionProductNo);
    void deleteBasketItem(String sessionUserID, int basketNo);
    void deleteBasketAll(String sessionUserID);
    void insertPurchaseAll(String sessionUserID);
    void insertPurchaseItem(String sessionUserID, int basketNo);

    List<ProductDTO> selectPurchaseList(String sessionUserID);

}
