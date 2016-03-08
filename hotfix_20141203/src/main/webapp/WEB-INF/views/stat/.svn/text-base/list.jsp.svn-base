<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
  <title>统计分析</title>
</head>
<body>
<div class="Container">
  <div id="chartDiv" class="Content fontText">
    <div style="float:right">
      <h2>最近30天使用次数</h2>
      <canvas id="countChart" width="700" height="350"></canvas>
    </div>
    <div>
      <h2>最近30天得分</h2>
      <canvas id="scoreChart" width="700" height="350"></canvas>
    </div>
    <div style="float:right">
      <h2>最近30天总用时</h2>
      <canvas id="totalTimeChart" width="700" height="350"></canvas>
    </div>
    <div>
      <h2>最近30天平均用时</h2>
      <canvas id="avgTimeChart" width="700" height="350"></canvas>
    </div>
  </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/charts.js"></script>
<script>
  $(function () {
    function getByLoginName(loginName) {
      $.getJSON('<%=request.getContextPath()%>/stat/list.action?dataType=user&showType=json&loginName=' + loginName, function (data) {
        // 使用次数
        var countData = {
          labels: data['dateStr'].data['dateStr'],
          datasets: [
            {
              fillColor: "rgba(151,187,205,0.5)",
              strokeColor: "rgba(151,187,205,1)",
              pointColor: "rgba(151,187,205,1)",
              pointStrokeColor: "#fff",
              data: data['times'].data['times']
            }
          ]
        }
        var ctx1 = $("#countChart").get(0).getContext("2d");
        var maxValue1 = data['times'].maxValue;
        new Chart(ctx1).Line(countData, {scaleOverride: true, scaleSteps: 10, scaleStepWidth: Math.ceil(maxValue1 / 10)});
        // 平均得分
        var scoreData = {
          labels: data['dateStr'].data['dateStr'],
          datasets: [
            {
              fillColor: "rgba(151,187,205,0.5)",
              strokeColor: "rgba(151,187,205,1)",
              pointColor: "rgba(151,187,205,1)",
              pointStrokeColor: "#fff",
              data: data['avgScore'].data['avgScore']
            }
          ]
        }
        var ctx2 = $("#scoreChart").get(0).getContext("2d");
        var maxValue2 = data['avgScore'].maxValue;
        new Chart(ctx2).Line(scoreData, {scaleOverride: true, scaleSteps: 10, scaleStepWidth: Math.ceil(maxValue2 / 10)});
        // 总用时
        var totalTimeData = {
          labels: data['dateStr'].data['dateStr'],
          datasets: [
            {
              fillColor: "rgba(151,187,205,0.5)",
              strokeColor: "rgba(151,187,205,1)",
              pointColor: "rgba(151,187,205,1)",
              pointStrokeColor: "#fff",
              data: data['totalTime'].data['totalTime']
            }
          ]
        }
        var ctx3 = $("#totalTimeChart").get(0).getContext("2d");
        var maxValue3 = data['totalTime'].maxValue;
        new Chart(ctx3).Line(totalTimeData, {scaleOverride: true, scaleSteps: 10, scaleStepWidth: Math.ceil(maxValue3 / 10)});
        // 平均用时
        var avgTimeData = {
          labels: data['dateStr'].data['dateStr'],
          datasets: [
            {
              fillColor: "rgba(151,187,205,0.5)",
              strokeColor: "rgba(151,187,205,1)",
              pointColor: "rgba(151,187,205,1)",
              pointStrokeColor: "#fff",
              data: data['avgTime'].data['avgTime']
            }
          ]
        }
        var ctx4 = $("#avgTimeChart").get(0).getContext("2d");
        var maxValue4 = data['avgTime'].maxValue;
        new Chart(ctx4).Line(avgTimeData, {scaleOverride: true, scaleSteps: 10, scaleStepWidth: Math.ceil(maxValue4 / 10)});
      });
    }

    getByLoginName('<e:property value="@loginName"/>');
    resizeCanvas();
  });
  function resizeCanvas() {
    var width = document.getElementById('chartDiv').offsetWidth;
    $('#countChart').attr('width', width / 2);
    $('#scoreChart').attr('width', width / 2);
    $('#totalTimeChart').attr('width', width / 2);
    $('#avgTimeChart').attr('width', width / 2);
  }
</script>
</body>
</html>