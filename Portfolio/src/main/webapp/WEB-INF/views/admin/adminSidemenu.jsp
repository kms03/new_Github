<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.container_side {
	width:200px;
	height:360px;
    margin-top: 0px;
    text-align: center;
    background-color: #fff;
    float: left;
    padding: 0px;
}

body {
    background: #fff;
    font-family: 'Open Sans', Arial, Helvetica, Sans-serif, Verdana, Tahoma
}

ul {
    list-style-type: none;
    padding: 0px;
    margin: 0px;
    text-align: center;
}

a {
    color: #b63b4d;
    text-decoration: none;
}

.accordion {
    width: 100%;
    max-width: 200px;
    
    background: #FFF;
    -webkit-border-radius: 4px;
    -moz-border-radius: 4px;
    border-radius: 0px;
}

.accordion .link {
    cursor: pointer;
    display: block;
    padding: 15px 15px 15px 42px;
    color: #4D4D4D;
    font-size: 14px;
    font-weight: 700;
    border-bottom: 1px solid #CCC;
    position: relative;
    -webkit-transition: all 0.4s ease;
    -o-transition: all 0.4s ease;
    transition: all 0.4s ease;
}

.accordion li:last-child .link {
    border-bottom: 0;
}

.accordion li i {
    position: absolute;
    top: 16px;
    left: 12px;
    font-size: 18px;
    color: #595959;
    -webkit-transition: all 0.4s ease;
    -o-transition: all 0.4s ease;
    transition: all 0.4s ease;
}

.accordion li i.fa-chevron-down {
    right: 12px;
    left: auto;
    font-size: 16px
}

.accordion li.open .link {
    color: #AA00FF;
}

.accordion li.open i {
    color: #AA00FF;
}

.accordion li.open i.fa-chevron-down {
    -webkit-transform: rotate(180deg);
    -ms-transform: rotate(180deg);
    -o-transform: rotate(180deg);
    transform: rotate(180deg)
}

.submenu {
    display: none;
    background: #444359;
    font-size: 14px;
}

.submenu li {
    border-bottom: 1px solid #4b4a5e;
    text-align: center;
}

.submenu a {
    display: block;
    text-decoration: none;
    color: #d9d9d9;
    padding: 12px;
    padding-left: 42px;
    -webkit-transition: all 0.25s ease;
    -o-transition: all 0.25s ease;
    transition: all 0.25s ease;
}

.submenu a:hover {
    background: #b63b4d;
    color: #FFF
}
</style>
<script>
$(function() {
	var Accordion = function(el, multiple) {
	this.el = el || {};
	this.multiple = multiple || false;

	var links = this.el.find('.link');

	links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
	}

	Accordion.prototype.dropdown = function(e) {
	var $el = e.data.el;
	$this = $(this),
	$next = $this.next();

	$next.slideToggle();
	$this.parent().toggleClass('open');

	if (!e.data.multiple) {
	$el.find('.submenu').not($next).slideUp().parent().removeClass('open');
	};
	}

	var accordion = new Accordion($('#accordion'), false);
	});
</script>
<div class="container_side">
    <ul id="accordion" class="accordion">
        <li>
            <div class="link"><i class="fa fa-laptop"></i>회원 관리<i class="fa fa-chevron-down"></i></div>
            <ul class="submenu">
                <li><a href="member_list">회원 리스트</a></li>
            </ul>
        </li>
        <li>
            <div class="link"><i class="fa fa-tv"></i>게시판 관리<i class="fa fa-chevron-down"></i></div>
            <ul class="submenu">
                <li><a href="admin_board">공지사항 게시판 관리</a></li>
                <li><a href="admin_board">게시판 관리</a></li>
            </ul>
        </li>
    </ul>
</div>