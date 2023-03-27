package please.spring.mapper;

import org.apache.ibatis.annotations.Mapper;
import please.spring.dto.ProductDTO;
import please.spring.paging.Criteria;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductDTO> selectPopulProduct() throws Exception;
    List<ProductDTO> selectNewProduct() throws Exception;
    List<ProductDTO> selectAllProduct(Criteria cri) throws Exception;
    List<ProductDTO> selectCategoryProduct(Criteria cri) throws Exception;
    int selectAllProductCnt();
    int selectCategoryProductCnt(String category);
    List<ProductDTO> selectProductRank(String category) throws Exception;

    ProductDTO selectDetailProduct(int productNo);
    List<ProductDTO> selectReviewSimpleInfoLimit(int productNo);
    List<ProductDTO> selectReviewSimpleInfo(int productNo);
    ProductDTO selectPurchaseReview(String sessionUserID, int purchaseNo);
    void updatePurchaseReview(int purchaseNo);
    void insertProduct(ProductDTO productDTO);
    void updateProduct(ProductDTO productDTO);
    void deleteProduct(ProductDTO productDTO);

    int selectProductOwner(String sessionUserID, int sessionProductNo);
    void insertReview(ProductDTO productDTO);
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
