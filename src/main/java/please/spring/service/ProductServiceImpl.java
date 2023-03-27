package please.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import please.spring.dto.ProductDTO;
import please.spring.mapper.ProductMapper;
import please.spring.paging.Criteria;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> selectPopulProduct() throws Exception {
        return productMapper.selectPopulProduct();
    }

    @Override
    public List<ProductDTO> selectNewProduct() throws Exception {
        return productMapper.selectNewProduct();
    }

    @Override
    public List<ProductDTO> selectAllProduct(Criteria cri) throws Exception {
        return productMapper.selectAllProduct(cri);
    }

    @Override
    public List<ProductDTO> selectCategoryProduct(Criteria cri) throws Exception {
        return productMapper.selectCategoryProduct(cri);
    }

    @Override
    public int selectAllProductCnt() throws Exception {
        return productMapper.selectAllProductCnt();
    }

    @Override
    public int selectCategoryProductCnt(String mainCategory) throws Exception {
        return productMapper.selectCategoryProductCnt(mainCategory);
    }

    @Override
    public List<ProductDTO> selectProductRank(String category) throws Exception {
        return productMapper.selectProductRank(category);
    }

    @Override
    public ProductDTO selectDetailProduct(int productNo) {
        return productMapper.selectDetailProduct(productNo);
    }

    @Override
    public List<ProductDTO> selectReviewSimpleInfoLimit(int productNo) {
        return productMapper.selectReviewSimpleInfoLimit(productNo);
    }
    @Override
    public List<ProductDTO> selectReviewSimpleInfo(int productNo) {
        return productMapper.selectReviewSimpleInfo(productNo);
    }

    @Override
    @Transactional
    public void insertProduct(ProductDTO productDTO)  {
        productMapper.insertProduct(productDTO);
    }

    @Override
    @Transactional
    public void updateProduct(ProductDTO productDTO)  {
        productMapper.updateProduct(productDTO);
    }

    @Override
    @Transactional
    public void deleteProduct(ProductDTO productDTO)  {
        productMapper.deleteProduct(productDTO);
    }

    @Override
    public int selectProductOwner(String sessionUserID, int sessionProductNo) {
        return productMapper.selectProductOwner(sessionUserID, sessionProductNo);
    }

    @Override
    public ProductDTO selectPurchaseReview(String sessionUserID, int purchaseNo) {
        return productMapper.selectPurchaseReview(sessionUserID, purchaseNo);
    }

    @Override
    public void updatePurchaseReview(int purchaseNo) {
        productMapper.updatePurchaseReview(purchaseNo);
    }

    @Override
    @Transactional
    public void insertReview(ProductDTO productDTO)  {
        productMapper.insertReview(productDTO);
    }

    @Override
    @Transactional
    public void refreshReview() {
        productMapper.refreshReview();
    }

    @Override
    public ProductDTO selectBasketUser(String sessionUserID) {
        return productMapper.selectBasketUser(sessionUserID);
    }

    @Override
    public List<ProductDTO> selectBasket(String sessionUserID) {
        return productMapper.selectBasket(sessionUserID);
    }

    @Override
    @Transactional
    public void insertBasket(String sessionUserID, int sessionProductNo) {
        productMapper.insertBasket(sessionUserID, sessionProductNo);
    }

    @Override
    @Transactional
    public void deleteBasketItem(String sessionUserID, int basketNo) {
        productMapper.deleteBasketItem(sessionUserID, basketNo);
    }
    @Override
    @Transactional
    public void deleteBasketAll(String sessionUserID) {
        productMapper.deleteBasketAll(sessionUserID);
    }

    @Override
    @Transactional
    public void insertPurchaseAll(String sessionUserID) {
        productMapper.insertPurchaseAll(sessionUserID);
    }

    @Override
    @Transactional
    public void insertPurchaseItem(String sessionUserID, int basketNo) {
        productMapper.insertPurchaseItem(sessionUserID, basketNo);
    }

    @Override
    public List<ProductDTO> selectPurchaseList(String sessionUserID) {
        return productMapper.selectPurchaseList(sessionUserID);
    }
}
