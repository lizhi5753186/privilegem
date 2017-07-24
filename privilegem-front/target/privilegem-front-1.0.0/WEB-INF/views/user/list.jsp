<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
    <div class="col-md-12">
        <!-- Begin: life time stats -->
        <div class="portlet">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-user"></i>帐户列表
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-container">
                    <div class="table-actions-wrapper">
                        <button class="btn btn-add blue table-group-action-submit" id="user-add" data-toggle="modal" onclick="javascript:User.add_click();"> 新增</button>
                    </div>
                    <table class="table table-striped table-bordered table-hover" id="datatable_cl">
                        <thead>
                        <tr>
                            <th class="col-sm-1" style="display: none">Id</th>
                            <th class="col-sm-1">用户名</th>
                            <th class="col-sm-1">真实姓名</th>
                            <th class="col-sm-1">性别</th>
                            <th class="col-sm-1">是否锁定</th>
                            <th class="col-sm-2">最后操作人</th>
                            <th class="col-sm-2">最后操作时间</th>
                            <th class="col-sm-1">修改</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="list" items="${users}">
                            <tr>
                                <td style="display: none">${list.id}</td>
                                <td>${list.username}</td>
                                <td>${list.fullname}</td><br/>
                                <td>${list.gender}</td><br/>
                                <td>${list.isLock}</td><br/>
                                <td>${list.updatePerson}</td><br/>
                                <td>${list.updateDate}</td><br/>
                                <td>
                                    <button class="btn btn-add blue table-group-action-submit" id="user-modify" data-toggle="modal" onclick="javascript:User.modify_click();"> 修改
                                    </button>
                                </td>
                            </tr>

                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</div>

