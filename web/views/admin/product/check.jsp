<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<!-- Bootstrap core JavaScript-->
<title>Share Things</title>

<!-- Custom fonts for this template-->
<link
	href="<%=request.getContextPath()%>/resource/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="<%=request.getContextPath()%>/resource/css/sb-admin-2.min.css"
	rel="stylesheet">
<style>
.even:hover {
	cursor: pointer;
}

#dataTable_wrapper {
	overflow: hidden;
}
</style>
</head>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- 사이드바 인클루드 -->
		<%@ include file="../common/sidebar.jsp"%>
		<!-- End of Sidebar -->

		<!--콘텐츠 영역 시작-->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- 메인 콘텐츠 영역 시작 -->
			<div id="content">
				<!-- 헤더 인클루드 -->
				<%@ include file="../common/header.jsp"%>
				<!-- 컨텐츠바디 영역 실제 작성 영역 -->
				<div class="container-fluid">
					<h1 class="h3 mb-2 text-gray-800">물품조회</h1>
					<img src="<%=request.getContextPath()%>/resource/img/adminHr.png"
						width="100%">
					<div class="row" class="col-lg-12">
						<div class="col-sm-12 col-lg-12">
							<div class="card shadow mb-4" id="filter" align="center">
								<div class="card-header py-3">조회 필터</div>
								<div class="card-body">
									<table class="col-lg-12" id="filterArea">
										<tr style="height: 20px">
											<td style="width: 90px">
												대분류 :
											</td>
											<td>
												<select style="heigth: 30px; width: 40%;">
													<option>전자기기</option>
													<option>유아동</option>
													<option>취미레져</option>
												</select>
												&nbsp;&nbsp;&nbsp;
												<input type="text" name="userId" style="width: 40%">
											</td>
											<td style="width: 90px">
												중분류 :
											</td>
											<td>
												<select style="heigth: 30px; width: 40%;">
													<option>디지털</option>
													<option>가전</option>
													<option>컴퓨터</option>
											</select>
											&nbsp;&nbsp;&nbsp;
											<input type="text" name="userId" style="width: 40%">
											</td>
											<td style="width: 90px">
												세분류 :
											</td>
											<td>
												<select style="heigth: 30px; width: 40%;">
													<option>노트북</option>
													<option>데스크탑</option>
											</select>
											&nbsp;&nbsp;&nbsp;
											<input type="text" name="userId" style="width: 40%">
											</td>
										</tr>
										<tr>
											<td style="width: 70px">등록기간 :</td>
											<td colspan="3"><input type="date" name="startD">&nbsp;&nbsp;&nbsp;
												~ &nbsp;&nbsp;&nbsp; <input type="date" name="endD">
											</td>
										</tr>
									</table>
									<div>
										<button>조회하기</button>
										&nbsp;&nbsp;&nbsp;
										<button>초기화</button>
									</div>
								</div>
							</div>
							<div class="card shadow mb-4">
                  <div class="card-body">
                     <div class="table-responsive">
                        <div id="dataTable_wrapper"
                           class="dataTables_wrapper dt-bootstrap4">
                           <div class="row">
                              <div class="col-sm-12">
                                 <table class="table table-bordered dataTable" id="dataTable"
                                    width="100%" cellspacing="0" role="grid"
                                    aria-describedby="dataTable_info" style="width: 100%;">
                                    <thead>
                                       <tr role="row">

                                          <th class="sorting_asc" tabindex="0"
                                             aria-controls="dataTable" rowspan="1" colspan="1"
                                             aria-label="Name: activate to sort column descending"
                                             aria-sort="ascending" style="width: 30px;">등록번호</th>
                                          <th class="sorting" tabindex="0" aria-controls="dataTable"
                                             rowspan="1" colspan="1"
                                             aria-label="Position: activate to sort column ascending"
                                             style="width: 30px;">등록자</th>
                                          <th class="sorting" tabindex="0" aria-controls="dataTable"
                                             rowspan="1" colspan="1"
                                             aria-label="Office: activate to sort column ascending"
                                             style="width: 30px;">물품명</th>
                                          <th class="sorting" tabindex="0" aria-controls="dataTable"
                                             rowspan="1" colspan="1"
                                             aria-label="Age: activate to sort column ascending"
                                             style="width: 30px;">등록기간</th>
                                          <th class="sorting" tabindex="0" aria-controls="dataTable"
                                             rowspan="1" colspan="1"
                                             aria-label="Start date: activate to sort column ascending"
                                             style="width: 30px;">상태</th>
                                       </tr>
                                    </thead>

                                    <tbody>
                                       <tr role="row" class="even">
                                          <td class="sorting_1">20190508</td>
                                          <td>user01</td>
                                          <td>노트북</td>
                                          <td>2019/05/10 ~ 2019/07/10</td>
                                          <td>렌탈중</td>
                                       </tr>
                                       <tr role="row" class="even">
                                          <td class="sorting_1">20190508</td>
                                          <td>user01</td>
                                          <td>노트북</td>
                                          <td>2019/05/10 ~ 2019/07/10</td>
                                          <td>렌탈중</td>
                                       </tr>
                                       <tr role="row" class="even">
                                          <td class="sorting_1">20190508</td>
                                          <td>user01</td>
                                          <td>노트북</td>
                                          <td>2019/05/10 ~ 2019/07/10</td>
                                          <td>렌탈중</td>
                                       </tr>
                                    </tbody>
                                 </table>
                              </div>
                           </div>
                           <%@ include file="../common/paging.jsp"%>
                        </div>
                     </div>
                  </div>
               </div>
			</div>
						<div class="modal fade" id="cancelModal" role="dialog">
							<div class="modal-dialog">

								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">요청 거절 처리</h4>
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>
									<div class="row">
										<div class="col-md-12 col-lg-12">
											<div class="modal-body">
												<p>물품명과 승인상태를 확인하고 처리하세요</p>
												<div class="panel-body">
													<table width="100%"
														class="table table-striped table-bordered table-hover"
														id="dataTables-example">
														<thead>
															<tr>
																<th style="width: 40px; text-align: center;"><input
																	type="checkBox"></th>
																<th style="text-align: center;"
																	class="text-black-50 small">등록요청번호</th>
																<th style="text-align: center;"
																	class="text-black-50 small">물품명</th>
																<th style="text-align: center;"
																	class="text-black-50 small">등록자</th>
																<th style="text-align: center; width: 130px"
																	class="text-black-50 small">거절사유</th>
															</tr>
														</thead>
														<tbody>
															<tr class="odd gradeX">
																<td><input type="checkBox">
																<td></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
														</tbody>
													</table>
												</div>
												<h5>*거절상세사유</h5>
												<textarea rows="10" cols="55" placeholder="EX)거짓 정보 등록"></textarea>
											</div>
											<div class="modal-footer">
												<button type="submit" class="btn btn-default"
													data-dismiss="modal">거절처리</button>
												<button type="button" class="btn btn-default"
													data-dismiss="modal">닫기</button>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>
						<!-- 메인 콘텐트 영역 끝 -->
						<!-- Footer 인클루드 -->
					</div>
					<%@ include file="../common/footer.jsp"%>
					<!-- 메인 콘텐츠 영역 끝 -->
				</div>
				<!-- 콘텐츠 영역 끝 -->
			</div>

			<!-- 맨위로-->
			<%@ include file="../common/toTop.jsp"%>

			<!-- 로그아웃 모달-->
			<%@ include file="../common/logoutModal.jsp"%>

			<script>
		$(function() {
			$(".even").click(function() {
				location = "<%=request.getContextPath()%>/views/admin/request/reqProductDetail.jsp";
				});
			});

			function ok() {
				alert("정말 승인하시겠습니까?");
			}
			</script>
			<script
				src="<%=request.getContextPath()%>/resource/vendor/jquery/jquery.min.js"></script>
			<script
				src="<%=request.getContextPath()%>/resource/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

			<!-- Core plugin JavaScript-->
			<script
				src="<%=request.getContextPath()%>/resource/vendor/jquery-easing/jquery.easing.min.js"></script>

			<!-- Custom scripts for all pages-->
			<script
				src="<%=request.getContextPath()%>/resource/js/sb-admin-2.min.js"></script>

			<!-- Page level plugins -->
			<script
				src="<%=request.getContextPath()%>/resource/vendor/chart.js/Chart.min.js"></script>

			<!-- Page level custom scripts -->
			<script
				src="<%=request.getContextPath()%>/resource/js/demo/chart-area-demo.js"></script>
			<script
				src="<%=request.getContextPath()%>/resource/js/demo/chart-pie-demo.js"></script>
</body>

</html>

