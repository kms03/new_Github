<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
	<form method="post" action="insert_member" class="signup-form" name="frm">
		<div class="padding container-fluid d-flex justify-content-center">
			<div class="col-md-4">
				<div class="free-quote bg-dark h-100">
					<h2 class="my-4 heading text-center">회원 가입</h2>
						<input type="hidden" name="reid">
						<div class="form-group">
							<label for="fq_name">아이디</label> &nbsp;&nbsp;
							<input type="button" value="중복 체크" id="search_id" onclick="idCheck()">
							<input type="text"
								class="form-control btn-block" id="id" name="id"
								placeholder="아이디를 입력해주세요">
						</div>
						
						<div class="form-group mb-4">
							<label for="fq_email">비밀번호</label> <input type="password"
								class="form-control btn-block" id="pwd" name="pwd"
								placeholder="비밀번호를 입력해주세요">
						</div>
						
						<div class="form-group mb-4">
							<label for="fq_email">비밀번호 확인</label> <input type="password"
								class="form-control btn-block" id="re_pwd" name="re_pwd"
								placeholder="비밀번호 확인을 입력해주세요">
						</div>
						
						<div class="form-group mb-4">
							<label for="fq_email">이름</label> <input type="text"
								class="form-control btn-block" id="name" name="name"
								placeholder="이름을 입력해주세요">
						</div>
						
						<div class="form-group mb-4">
							<label for="fq_email">이메일</label> <input type="text"
								class="form-control btn-block" id="email" name="email"
								placeholder="이메일을 입력해주세요">
						</div>
						
						<div class="form-group mb-4">
							<label for="fq_email">우편번호</label>&nbsp;&nbsp;
							<input type="button" value="주소 찾기" id="search_addr" onclick="addr_search()">
							<input type="text"
								class="form-control btn-block" id="zip_num" name="zip_num"
								placeholder="주소 찾기 버튼을 눌러주세요" readonly="readonly">
						</div>
						
						<div class="form-group mb-4">
							<label for="fq_email">주소</label> <input type="text"
								class="form-control btn-block" id="addr1" name="addr1"
								placeholder="" readonly="readonly">
						</div>
						
						<div class="form-group mb-4">
							<label for="fq_email">상세 주소</label> <input type="text"
								class="form-control btn-block" id="addr2" name="addr2"
								placeholder="상세 주소를 입력해주세요">
						</div>
						
						<div class="form-group mb-4">
							<label for="fq_email">전화 번호</label> <input type="text"
								class="form-control btn-block" id="phone" name="phone"
								placeholder="전화 번호를 입력해주세요">
						</div>
						
						<div class="form-group">
							<input type="button"
								class="btn btn-primary text-white py-2 px-4 btn-block"
								value="가입 하기" onclick="join_check()">
						</div>
				</div>
			</div>
		</div>
	</form>
	<%@ include file="../footer.jsp"%>