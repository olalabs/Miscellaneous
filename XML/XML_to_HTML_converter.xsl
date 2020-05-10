<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="document">
	<html>
	<head>
		<style>
			table, th, td { border: 1px solid; border-collapse: collapse; padding: 8px; }
		</style>
	</head>
	<body>
		<h1>Lista osób</h1>
		<xsl:apply-templates select="personlist"/>
	</body>
	</html>
</xsl:template>

<xsl:template match="personlist">
	<table>
		<tr>
			<th>Imię</th>
			<th>Nazwisko</th>
			<th>Pesel</th>
			<th>Data urodzenia</th>
			<th>Płeć</th>
			<th>Ulica</th>
			<th>Nr domu</th>
			<th>Nr mieszkania</th>
			<th>Miasto</th>
			<th>Kod pocztowy</th>
			<th>Nr telefonu</th>
			<th>Email</th>
			<th>Zawód</th>
		</tr>
		<xsl:apply-templates select="person"/>
	</table>
</xsl:template>

<xsl:template match="person">
	<tr>
		<xsl:apply-templates select="firstname | lastname | pesel | birthdate | sex | address | phone | email | profession" />
	</tr>
</xsl:template>

<xsl:template match="address">
	<xsl:apply-templates select="street | housenumber | suite | city | postalcode "/>
</xsl:template>


<xsl:template match="firstname | lastname | pesel | birthdate | sex | phone | email | profession | street | housenumber | suite | city | postalcode">
	<td><xsl:value-of select="."/></td>
</xsl:template>

</xsl:stylesheet>
