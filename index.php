<!DOCTYPE html>
<html>
<head>
	<title>PHP Starter Application</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="style.css" />
</head>
<body>
	<table>
		<tr>
			<td style='width: 30%;'>
				<img class = 'newappIcon' src='images/newapp-icon.png'>
			</td>
			<td>
				<h1 id = "message"><?php echo "Welcome to the sampleTeam project for Winona CS410"; ?></h1>
				<p class='description'> 
				Thanks for visiting.
				</p>
				<ul>
				<li><a href="/SRS">SRS</a></li>
				<li><a href="/SoftwareDesign">Software Design</a></li>
				<li><a href="/TestPlan">Test Plan</a></li>
				
				</ul>
			</td>
		</tr>
	</table>
	Authorized users can access the project using 
	<a href="https://console.bluemix.net/devops/code/edit/edit.html?env_id=ibm:yp:us-south#/devops/code/file/jeber@us.ibm.com-34bcc589272d4b728bb8fe9336a4fe11/larp2-cs410-winona/">
	this link </a>
</body>
</html>
