<!DOCTYPE HTML>
<html>

<head>
    <title>Example Page title</title>
    <meta charset="UTF-8" />
    <meta name="description" content="Example Page Description" />
    <meta name="keywords" content="Example Page Keywords" />
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<link rel="stylesheet" href="pdf/css/style.css" />
	
	<style>
		div {
		    background-color: red;
		}
	</style>

</head>

<body>
 	
		<#if data.users?? && data.users?size gte 1>
			<#list data.users as user>
	       		<div class="names">
	       			Name: ${user.name!"-"}
	       		</div>
	       	</#list>
	     <#else>
	     	<div class="names">
	       		No names
	       	</div>
	    </#if>
	

	<#if data.users ??> 
            
		<table>
			<tr>
				<th>Name</th>
				<th>Surname</th>
			</tr>
			<#list data.users as user>
				<tr>
					<td>${user.name!"-"}</td>
					<td>${user.surname!"-"}</td>
				</tr>
			</#list>
		</table>
	</#if>
	
	<img src="pdf/images/image.png" />
	

</body>

</html>