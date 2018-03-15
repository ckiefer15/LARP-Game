<!DOCTYPE html>
<html>
<head>
	<title>Software Design for Team Project</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" href="style.css" />
</head>
<body>
<div>
<h1>Software Design for Team Project</h1>
</div>
<hr>
<div>
<h2>Table of Contents</h2>
<ul>
<li><a href="#Preface">Preface</a></li> 
<li><a href="#Context">Context</a></li> 
<li><a href="#UserInterfaces">User Interfaces</a></li> 
<li><a href="#DatabaseSchemaDesign">Database Schema Design</a></li> 
<li><a href="#PrincipleObjects">PrincipleObjects</a></li> 
<li><a href="#UMLDiagrams">UML Diagrams</a></li> 
<li><a href="#ContributionsSummary">Contributions Summary</a></li> 
<li><a href="#AcceptanceParagraph">Acceptance Paragraph</a></li> 
<li><a href="#TestAndCodingAssignments">Test and Coding Assignments</a></li> 

</ul>
</div>

<?PHP
			
foreach (glob("*.html") as $filename) {
	   echo "<hr>\n"; 
	   $filecontents = file_get_contents($filename);
	   echo "<div>";
       echo "$filecontents";
	   echo "</div>";
}

?>	
<hr>
</body>
</html>
