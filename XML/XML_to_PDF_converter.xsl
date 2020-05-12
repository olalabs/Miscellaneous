<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:template match="document">
	<fo:root>
		<fo:layout-master-set>
			<!-- wzorzec strony-->
			 <fo:simple-page-master master-name="A4" page-width="297mm" page-height="210mm" margin-top="1cm" margin-bottom="1cm" margin-left="1cm" margin-right="1cm">
				<fo:region-body region-name="xsl-region-body" margin="2cm"/>
				<fo:region-before region-name="xsl-region-before" extent="2cm"/>
				<fo:region-after region-name="xsl-region-after" extent="2cm"/>
				<fo:region-start extent="2cm"/>
				<fo:region-end extent="2cm"/>
			 </fo:simple-page-master>		 
		</fo:layout-master-set>
		
		<!-- tresc dokumentu-->
		<fo:page-sequence master-reference="A4" id="siteNumber" font-size="9pt">
			<!-- bloki z zawartością strony w elementach static-content i flow -->
			
			<fo:static-content flow-name="xsl-region-before">
				<fo:block>Lista osob</fo:block>
			</fo:static-content>
			 
			<fo:static-content flow-name="xsl-region-after">
				<fo:block text-align="end"> 
						<xsl:text> Strona </xsl:text> <fo:page-number/> <xsl:text> z </xsl:text> <fo:page-number-citation-last ref-id="siteNumber"/>
				</fo:block>	
			</fo:static-content>
			
			<fo:flow flow-name="xsl-region-body">
				<xsl:apply-templates select="personlist"/>
			</fo:flow>
		</fo:page-sequence>	
	</fo:root>
</xsl:template>

<xsl:template match="personlist">
	<fo:table table-layout="fixed" border ="solid 0.1mm black">
	<fo:table-column column-number="1" column-width="7%"/>
	<fo:table-column column-number="2" column-width="10%"/>
	<fo:table-column column-number="3" column-width="10%"/>
	<fo:table-column column-number="4" column-width="10%"/>
	<fo:table-column column-number="5" column-width="3%"/>
	<fo:table-column column-number="6" column-width="7%"/>
	<fo:table-column column-number="7" column-width="4%"/>
	<fo:table-column column-number="8" column-width="5%"/>
	<fo:table-column column-number="9" column-width="7%"/>
	<fo:table-column column-number="10" column-width="7%"/>
	<fo:table-column column-number="11" column-width="10%"/>
	<fo:table-column column-number="12" column-width="13%"/>
	<fo:table-column column-number="13" column-width="10%"/>
	
	<fo:table-header>
		<fo:table-row>
			<fo:table-cell border ="solid 0.1mm black"><fo:block font-weight="bold">Imie</fo:block></fo:table-cell>
			<fo:table-cell border ="solid 0.1mm black"><fo:block font-weight="bold">Nazwisko</fo:block></fo:table-cell>
			<fo:table-cell border ="solid 0.1mm black"><fo:block font-weight="bold">Pesel</fo:block></fo:table-cell>
			<fo:table-cell border ="solid 0.1mm black"><fo:block font-weight="bold">Data urodzenia</fo:block></fo:table-cell>
			<fo:table-cell border ="solid 0.1mm black"><fo:block font-weight="bold">Plec</fo:block></fo:table-cell>
			<fo:table-cell border ="solid 0.1mm black"><fo:block font-weight="bold">Ulica</fo:block></fo:table-cell>
			<fo:table-cell border ="solid 0.1mm black"><fo:block font-weight="bold">Nr domu</fo:block></fo:table-cell>
			<fo:table-cell border ="solid 0.1mm black"><fo:block font-weight="bold">Nr lokalu</fo:block></fo:table-cell>
			<fo:table-cell border ="solid 0.1mm black"><fo:block font-weight="bold">Miasto</fo:block></fo:table-cell>
			<fo:table-cell border ="solid 0.1mm black"><fo:block font-weight="bold">Kod pocztowy</fo:block></fo:table-cell>
			<fo:table-cell border ="solid 0.1mm black"><fo:block font-weight="bold">Nr telefonu</fo:block></fo:table-cell>
			<fo:table-cell border ="solid 0.1mm black"><fo:block font-weight="bold">Email</fo:block></fo:table-cell>
			<fo:table-cell border ="solid 0.1mm black"><fo:block font-weight="bold">Zawod</fo:block></fo:table-cell>	
		</fo:table-row>
	</fo:table-header>
	
	<fo:table-body>
		<xsl:apply-templates select="person"/>
	</fo:table-body>
	
	</fo:table>
</xsl:template>

<xsl:template match="person">
	 <fo:table-row>
		<xsl:apply-templates select="firstname | lastname | pesel | birthdate | sex | address | phone | email | profession" />
	 </fo:table-row>
</xsl:template>

<xsl:template match="address">
	<xsl:apply-templates select="street | housenumber | suite | city | postalcode "/>
</xsl:template>

<xsl:template match="firstname | lastname | pesel | birthdate | sex | phone | email | profession | street | housenumber | suite | city | postalcode">
	<fo:table-cell border ="solid 0.1mm black"><fo:block><xsl:value-of select="."/></fo:block></fo:table-cell>
</xsl:template>

</xsl:stylesheet>
