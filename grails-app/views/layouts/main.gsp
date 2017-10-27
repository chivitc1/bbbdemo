<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  		<asset:stylesheet src="application.css"/>
		<asset:stylesheet src="hubbub/hubbub.css"/>
		<asset:javascript src="application.js"/>
		<g:layoutHead/>
	</head>
	<body>
		<div>
			<div id="header">
				<g:link uri="/">
					<asset:image id="logo" src="hubbub/headerlogo.png" alt="hubbub logo" />
				</g:link>
			</div>
			<div class="nav" role="navigation">
				<ul style="list: unstyled-list">
					<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				</ul>

				<div id="login" style="display: inline-block; float:right">
					<g:if test="${!session?.user}">
						<g:render template="/login/loginForm" model="user" />
					</g:if >
					<g:if test="${session?.user}">
						Welcome to ${session.user?.fullName} | <g:link controller="login" action="logout">Logout</g:link>
					</g:if>
				</div>
			</div>

			<div id="body"><!-- start body -->
			<g:layoutBody/>
			</div> <!-- end body -->

			<div id="footer">
				<div id="footerText">INET - BBB Demo</div>
			</div>

		</div>

	</body>
</html>
