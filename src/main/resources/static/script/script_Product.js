$(function(){
	
    // 서비스 상세 페이지에서 서비스 이미지 영역 hover 시 Action (/product/detailProduct)
    let orgSrc = $("#mainProductImg").attr("src");
    $(".subProductImg").hover(function(){
        $("#mainProductImg").attr("src", $(this).attr("src"));
        }, function(){
        $("#mainProductImg").attr("src", orgSrc);
    });

	// 장바구니 담기 버튼 클릭 시 Action (/product/detailProduct)
    $("#addBasketBtn").click(function(){
        alert('상품을 장바구니에 담았습니다.');
    });

    // 장바구니 개별 구매 버튼 클릭 시 Action (/product/basketProduct)
	$("#purchaseBasketItemBtn").click(function(){
        alert('해당 상품 구매가 완료되었습니다.');
	});

    // 장바구니 개별 삭제 버튼 클릭 시 Action (/product/basketProduct)
    $("#delBasketItemBtn").click(function(){
        alert('장바구니에서 삭제되었습니다.');
    });

	// 장바구니 전체 삭제 버튼 클릭 시 Action (/product/basketProduct)
	$("#deleteAllBtn").click(function(){
		let choice = confirm('전체 상품 목록을 삭제하시겠습니까?')

		if(choice) {
			$("#deleteBasketAll").attr("href","/deleteBasketAll");
			$("#deleteBasketAll").get(0).click();
			alert('전체 상품 목록 삭제가 완료되었습니다.');
		} else {
            alert('전체 상품 목록 삭제를 취소하셨습니다.');
		}
	});

	// 장바구니 전체 구매 버튼 클릭 시 Action (/product/basketProduct)
	$("#buyBasketBtn").click(function(){
		let choice = confirm('전체 상품을 구매하시겠습니까?')

		if(choice) {
			$("#insertPurchaseBasket").attr("href","/insertPurchaseBasket");
			$("#insertPurchaseBasket").get(0).click();
			alert('전체 상품 구매가 완료되었습니다.');
		} else {
            alert('전체 상품 구매를 취소하셨습니다.');
		}
	});

	// 리뷰 등록하기 버튼 클릭 시 Action (/product/addReview)
	$("button#reviewSbmBtn").click(function(){
	    $("#reviewFrm").attr("action","/addReview");
    	$("#reviewFrm").submit();

    	opener.location.reload(true);

    	setTimeout(function(){
		    window.close();
    	},100);
	});

	// 리뷰 취소하기 버튼 클릭 시 Action (/product/addReview)
	$("button#reviewCancelBtn").click(function(){
		window.close();
	});
	
});


