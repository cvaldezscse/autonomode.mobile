<#-- this is a freemarker template -->
<html>
  <head>
    <title>${testreport.name} Test Report</title>
    <style type="text/css">
        table
        {
          table-layout:fixed;
          width:100%;
          word-break: break-word;
        }
    </style>
  </head>
  <body bgcolor="20b2aa">
  <div style="background-color: #20b2aa">
    <table BORDER="1" bordercolor="FDFEFE" style="width:100%; height: 25%" align="center" bgcolor="20b2aa">
        <tr>
             <th colspan="100%" align="center"><img src="test_logo.png" alt="YOUR_TEST LOGO"></th>
        </tr>
        <tr>
             <th colspan="25%">${testreport.name} Test Report</th>
             <th colspan="25%">${testreport.info}</th>
             <th colspan="25%"><a href="${testreport.repo}">Repository</a></th>
             <th colspan="25%">${testreport.date}</th>
        </tr>
        <tr>
             <th colspan="100%">Execution Summary</th>
        </tr>
        <tr>
             <th colspan="20%">Version</th>
             <th colspan="20%">Build</th>
             <th colspan="20%">Total Test</th>
             <th colspan="20%"><font color="green"><strong>Pass Rate</strong></font></th>
             <th colspan="20%"><font color="red"><strong>Fail Rate</strong></font></th>
        </tr>
        <tr>
             <td colspan="20%" align="center">${testreport.version}</td>
             <td colspan="20%" align="center">${testreport.build}</td>
             <td colspan="20%" align="center">${testreport.totalTest}</td>
             <td colspan="20%" align="center"><font color="green"><strong>${testreport.passRate}%</strong></font></td>
             <td colspan="20%" align="center"><font color="red"><strong>${testreport.failRate}%</strong></font></td>
        </tr>
        </table>
      <br>
      <table BORDER="1" bordercolor="20b2aa" bgcolor="FDFEFE" style="width:100%; height: 100%" align="left">
        <tr>
          <th bgcolor="20b2aa" height="25">Test Name</th>
          <th bgcolor="20b2aa" height="25">Expected Assertions</th>
          <th bgcolor="20b2aa" height="25">Steps</th>
          <th bgcolor="20b2aa" height="25">Raw Response</th>
          <th bgcolor="20b2aa" height="25">Description</th>
          <th bgcolor="20b2aa" height="25">Result</th>
        </tr>
        <#list testreport.testDetailsList as testdetail>
            <tr>
               <td bgcolor="${testdetail.resultColor}">
                    <a href="${testdetail.jamaURL}">${testdetail.jamaId}</a>
                    ${testdetail.testName}
               </td>
               <td>
                    <div class=scrollable>
                         <#list testdetail.assertions as assertions>
                              ${assertions.reason} <br>
                              ${assertions.expected} <br>
                              ${assertions.actual} <br>
                              <br>
                         </#list>
                     </div>
               <td>
                    <div class=scrollable>
                         <#list testdetail.steps as step>
                             ${step} <br><br>
                         </#list>
                    </div>
               </td>
               <td>
                    <div class=scrollable>
                         <#list testdetail.logs as log>
                             ${log} <br><br>
                         </#list>
                         <#list testdetail.screenshots as screenshot>
                             <center><img src="screenshots/${screenshot}" height="20%" width="100%"></center><br>
                         </#list>
                    </div>
               </td>
               <td>
                    <p>${testdetail.description}</p>
               </td>
               <td bgcolor="${testdetail.resultColor}">
                    ${testdetail.result}
               </td>
            </tr>
        </#list>
      </table>
    </div>
  </body>
</html>