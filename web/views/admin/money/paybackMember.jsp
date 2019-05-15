<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.kh.st.member.model.vo.*, com.kh.st.common.*"%>
<%	
	ArrayList<Payback> list = (ArrayList<Payback>) request.getAttribute("list");
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%> 
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

#filter {
	margin-top: 50px;
}

#filterArea td {
	padding: 5px;
}
.paging {
	margin-left:auto;
	margin-right:auto;
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
					<h1 class="h3 mb-2 text-gray-800">수익금 환급</h1>
					<img src="<%=request.getContextPath()%>/resource/img/adminHr.png"
						width="100%">
					<div class="row" id="filter" align="center">
						<div class="col-lg-12">
							<div class="card shadow mb-4">
								<div class="card-header py-3">조회 필터</div>
								<div class="card-body">
									<table class="col-lg-12" id="filterArea">
										<tr>
											<td width="5%">회원ID :</td>
											<td><input type="text" name="memberId"
												style="width: 80%"></td>
											<td width="5%">환급일 :</td>
											<td><input type="date" name="startDate"
												style="width: 40%"> &nbsp; ~ &nbsp;<input
												type="date" name="endDate" style="width: 40%"></td>
											<td width="5%">금액 :</td>
											<td><input type="number" name="startMoney"
												style="width: 40%"> &nbsp; ~ &nbsp;<input
												type="number" name="endMoney" style="width: 40%"></td>
											<td width="5%">상태 :</td>
											<td><select style="width: 80%">
													<option value="A">전체</option>
													<option value="N">환급대기</option>
													<option value="Y">환급완료</option>
											</select></td>
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
													<table class="table table-bordered dataTable"
														id="dataTable" width="100%" cellspacing="0" role="grid"
														aria-describedby="dataTable_info" style="width: 100%;"
														style="height:100px;">
														<thead>
															<tr role="row">
																<th width="1%"><input type="checkbox"></th>
																<th class="sorting_asc" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-sort="ascending"
																	aria-label="Name: activate to sort column descending"
																	style="width: 3%;">No.</th>
																<th class="sorting_asc" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-sort="ascending"
																	aria-label="Name: activate to sort column descending"
																	style="width: 5%;">회원ID</th>
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Office: activate to sort column ascending"
																	style="width: 20%;">계좌번호</th>
																<th class="sorting_asc" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-sort="ascending"
																	aria-label="Name: activate to sort column descending"
																	style="width: 7%;">은행명</th>
																<th class="sorting_asc" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-sort="ascending"
																	aria-label="Name: activate to sort column descending"
																	style="width: 5%;">예금주</th>
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Salary: activate to sort column ascending"
																	style="width: 10%;">금액</th>	
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Office: activate to sort column ascending"
																	style="width: 10%;">신청일</th>
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Age: activate to sort column ascending"
																	style="width: 10%;">환급일</th>
																<th class="sorting" tabindex="0"
																	aria-controls="dataTable" rowspan="1" colspan="1"
																	aria-label="Salary: activate to sort column ascending"
																	style="width: 7%;">상태</th>
															</tr>
														</thead>
														<tbody>
															<% for(Payback p : list){ %>
																<tr>
																	<td><input type="checkbox"></td>
																	<td><%= p.getPbNo() %></td>
																	<td><%= p.getUserId() %></td>
																	<td><%= p.getAccount() %></td>
																	<td><%= p.getBank() %></td>
																	<td><%= p.getAccName() %></td>
																	<td><%= p.getPbAmount() %></td>
																	<td><%= p.getReqDate() %></td>
																	<td><%= p.getPbDate() %></td>
																	<td><%= p.getPbStatus() %></td>
																</tr>
															<% } %>
														</tbody>
													</table>
												</div>
											</div>
											<div class="row">
												<div class="paging">
													<div class="col-lg-12">
														<div class="dataTables_paginate paging_simple_numbers"
															id="dataTable_paginate">
															<ul class="pagination">
																<li class="paginate_button page-item"
																	id="dataTable_first"><a
																	href="<%=request.getContextPath()%>/selectPaybackList.me?currentPage=1"
																	aria-controls="dataTable" data-dt-idx="0" tabindex="0"
																	class="page-link">First</a></li>
										
																<%
																		if (currentPage <= 1) {
																%>
																<li class="paginate_button page-item disabled"
																	id="dataTable_previous"><a
																	href="<%=request.getContextPath()%>/selectPaybackList.me?currentPage=<%=currentPage - 1%>"
																	aria-controls="dataTable" data-dt-idx="0" tabindex="0"
																	class="page-link">Previous</a></li>
																<%
																		} else {
																%>
																<li class="paginate_button page-item"
																	id="dataTable_previous"><a
																	href="<%=request.getContextPath()%>/selectPaybackList.me?currentPage=<%=currentPage - 1%>"
																	aria-controls="dataTable" data-dt-idx="0" tabindex="0"
																	class="page-link">Previous</a></li>
																<%
																		}
																%>
																<%
																		for (int p = startPage; p <= endPage; p++) {
																		    if (p == currentPage) {
																%>
																<li class="paginate_button page-item disabled"><a href="<%=request.getContextPath()%>/selectPaybackList.me?currentPage=<%=p%>"
																	aria-controls="dataTable" data-dt-idx="1" tabindex="0"
																	class="page-link"><%=p%></a></li>
																<%
																		} else {
																%>
																<li class="paginate_button page-item active"><a href="<%=request.getContextPath()%>/selectPaybackList.me?currentPage=<%=p%>"
																	aria-controls="dataTable" data-dt-idx="1" tabindex="0"
																	class="page-link"><%=p%></a></li>
																<%
																		}
																%>
										
										
																<%
																		}
																%>
																
																<%
																		if (currentPage >= maxPage) {
																%>
																<li class="paginate_button page-item disabled" id="dataTable_next"><a
																	href="<%=request.getContextPath()%>/selectPaybackList.me?currentPage=<%=currentPage + 1%>" aria-controls="dataTable" data-dt-idx="7" tabindex="0"
																	class="page-link">Next</a></li>
																<%
																		} else {
																%>
																<li class="paginate_button page-item next" id="dataTable_next"><a
																	href="<%=request.getContextPath()%>/selectPaybackList.me?currentPage=<%=currentPage + 1%>" aria-controls="dataTable" data-dt-idx="7" tabindex="0"
																	class="page-link">Next</a></li>
																<%      }     %>
																<li class="paginate_button page-item next" id="dataTable_end"><a
																	href="<%=request.getContextPath()%>/selectPaybackList.me?currentPage=<%=maxPage%>" aria-controls="dataTable" data-dt-idx="7" tabindex="0"
																	class="page-link">End</a></li>
															</ul>
														</div>
													</div>
												</div>
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
