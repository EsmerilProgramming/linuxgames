<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization groups="admins"/>
<c:set var="buscaUm"><%=String.valueOf(Constantes.ACTION_BUSCA)%></c:set>
<c:import url="cabecalho.jsp"/>
<html>
<body>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="aplicacao.titulo.win"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="aplicacaoWin.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="aplicacaoWin.novo.action" method="post">
 </c:otherwise>
</c:choose>

 <div class="row">
  <span class="label">
   <mtw:i18n key="aplicacao.nome"/>:
  </span>
  <mtw:input type="text" name="nome" size="50" maxlength="150" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="nome"/></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="aplicacao.link"/>:
  </span>
  <mtw:input type="text" name="link" size="50" maxlength="150" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="link" /></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="aplicacao.licenca"/>:
  </span>
  <mtw:select name="license_id" list="LICENCAS" />
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="aplicacao.subgrupo"/>:
  </span>
  <mtw:select name="subgroup_id" list="SUBGRUPOS" />
 </div>
 <div class="spacer">
  &nbsp;
  </div>

 <div class="row">
  <span class="botoes">

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
    <input type="submit" name="tipoCRUD" value="Alterar" />
    <mtw:input type="hidden" name="id" />
    <input type="submit" name="tipoCRUD" value="Remover" /></form>
    <form name="formulario" action="aplicacaoWin.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
  </span>
 </div>

</div>
 </c:when>
 <c:otherwise>
   <input type="submit" value="Cadastrar" />
  </span>
 </div>

</div>

<br><br>
<div class="formCadastro">

<mtw:list value="aplicacoes">
 <h1 class="cadastro"><mtw:i18n key="aplicacao.lista"/></h1>
 <mtw:isEmpty>
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </mtw:isEmpty>
 <mtw:paginator size="50" value="aplicacoes" >
 <mtw:loop>
   <ul id="resultlist">
     <li>
       <a href="aplicacaoWin.buscaUm.action?id=<mtw:out value="id"/>"><mtw:out value="nome"/> - <mtw:out value="link"/></a>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="aplicacaoWin.remove.action?id=<mtw:out value="id"/>">X</a> ]
     </li>
   </ul>
 </mtw:loop>
   <br><br>
   <div class="c">
    <mtw:hasPrevious><a href="aplicacaoWin.action?page=<mtw:out />"><img src="/img/anterior.png" alt="" width="16" height="16"/></a></mtw:hasPrevious>
    <mtw:pageNumbers pagesToShow="10">
     <mtw:isCurrPage><b><font size="+1"><mtw:out /></font></b></mtw:isCurrPage>
     <mtw:isCurrPage negate="true"><a href="aplicacaoWin.action?page=<mtw:out />"><mtw:out /></a></mtw:isCurrPage>
    </mtw:pageNumbers>
    <mtw:hasNext><a href="aplicacaoWin.action?page=<mtw:out />"><img src="/img/posterior.png" alt="" width="16" height="16"/></a></mtw:hasNext>
    <mtw:isEmpty negate="true">
     <h1 class="cadastro"><mtw:i18n key="aplicacao.resultados"/> <mtw:resultFrom /> - <mtw:resultTo /> <mtw:i18n key="aplicacao.resultadosDe"/> <mtw:resultTotal /></h1>
    </mtw:isEmpty>
   </div>
  </mtw:paginator>
 </mtw:list>

</div>
 </c:otherwise>
</c:choose>
</form>
</body>
</html>