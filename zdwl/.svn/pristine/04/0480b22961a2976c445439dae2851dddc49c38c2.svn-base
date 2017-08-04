$(function(){

	// 筛选按钮效果
	$('#j-filter-btn').click( function (e) {
		$(this).addClass('filter-clicked');
		$('#j-popup-box').show();
		e.stopPropagation();
	});
	$('#j-popup-box').find('button').click( function (e) {
		$('#j-popup-box').hide();
		$('#j-filter-btn').removeClass('filter-clicked');
		e.stopPropagation();
	});
	$('.main').click( function () {
		$('#j-popup-box').hide();
		$('#j-filter-btn').removeClass('filter-clicked');
	});
	$('.main').height($(window).height() - $('.header-main').height());

	// 去除最后一个dl的边框
	$('.main-box-cont').find('.dl-line:last').css('border-bottom', 'none');

	// 品牌锚点
	$('#j-brand-value').click( function (e) {
		$('#j-popup-brand').show();
		$('#j-popup-brand-bar').css('top', ($(window).height() + $('.header-main').height() - $('#j-popup-brand-bar').height())/2);
		e.stopPropagation();
	});
	$('#j-popup-brand-bar').find('li').click( function (e) {
		var $brandTitle = $('#j-popup-brand').find('.car-brand-title').eq($(this).index());
		$(window).scrollTop($brandTitle.offset().top - $('.header-main').height());
		e.stopPropagation();
	});
	$('#j-popup-brand').find('.car-brand-content li').click( function (e) {
		$('#j-brand-value').find('input').val($(this).find('span').text());
		$('#j-popup-brand').hide();
		e.stopPropagation();
	});

	// select下拉框
	$('.select-line').find('select').change( function (e) {
		$(this).siblings('input').val($(this).find('option:selected').text());
		e.stopPropagation();
	});
	$('#j-popup-box').click( function (e) {
		e.stopPropagation();
	});
	
	// 车辆详情图片预览效果
	$('.car-img').click( function () {
		$('#j-img-zoom').show().find('img').attr('src', $(this).attr('src'));
		$('.car-detail').hide();
	});
	$('#j-img-zoom').click( function () {
		$(this).hide().find('img').attr('src', '');
	    $('.car-detail').show();
	});
	
	// 车辆详情图片预览效果
	$('.interior-cart-item-img').click( function () {
		$('#j-img-zoom').show().find('img').attr('src', $(this).attr('src'));
		$('.interior-cart-item-img-detail').hide();
	});
	$('#j-img-zoom').click( function () {
		$(this).hide().find('img').attr('src', '');
	    $('.interior-cart-item-img-detail').show();
	});

})