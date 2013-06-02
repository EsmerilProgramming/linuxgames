package br.com.linuxgames.controller.crud;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mentawai.converter.Converter;
import org.mentawai.converter.DateConverter;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.model.dao.VersaoDeDistroDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.DistroVO;
import br.com.linuxgames.model.vo.VersaoDeDistroVO;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.DateHelper;


public class VersaoDeDistroAction extends BaseAction implements RedirectAfterLogin {

    VersaoDeDistroDAO dao = VersaoDeDistroDAO.getinstance();
	Logger logger = Logger.getLogger("br.com.linuxgames.controller.VersaoAction");

	private VersaoDeDistroVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeVersaos() {
    	Collection<?> collection = null;
    	try {
    		collection = dao.buscaTodos();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
	    return collection;
	}

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {
    	output.setValue("versoesDeDistros", listaDeVersaos());
    	return SUCCESS;
    }

    /**
     * action que busca um registro
     * @return
     * @throws ActionException
     */
    public String buscaUm() throws ActionException {

    	output.setValue("actionCRUD", String.valueOf(Constantes.ACTION_BUSCA));

    	vo.setId( input.getInt("id"));

    	try {
    		VersaoDeDistroVO storedVO = (VersaoDeDistroVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId()+" Nome="+storedVO.getRelease());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("release", storedVO.getRelease());
    		output.setValue("dataLancamento", DateHelper.mysqlFormat2EuropeanFormat(storedVO.getDataLancamento().toString()));
    		output.setValue("link", storedVO.getLink());
    		output.setValue("obs", storedVO.getObs());
    		output.setValue("tipo_id",  String.valueOf(storedVO.getDistro().getId()));

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("versoesDeDistros", listaDeVersaos());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

     	String jogoID= input.getString("tipo_id");
     	DistroVO jogoVO = new DistroVO();
     	jogoVO.setId(Integer.parseInt(jogoID));
     	vo.setDistro(jogoVO);

    	String dateStr = input.getString("dataLancamento");
    	Date dataLancamento = (Date) DateHelper.formatStrDate(dateStr);
     	vo.setDataLancamento(new java.sql.Date( dataLancamento.getTime()));

    	// chama o dao e traz o resultado
    	logger.info(" Versao: nome="+vo.getRelease());
    	try {
    		dao.adiciona(vo);
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("versoesDeDistros", listaDeVersaos());
		resetForm();
		return SUCCESS;
    }

	/**
	 *action que atualiza um registro
	 * @return
	 * @throws Exception
	 */
    public String atualiza() throws Exception {

    	// ajusta o VO
    	vo.setId( input.getInt("id"));
     	String jogoID= input.getString("tipo_id");
     	DistroVO jogoVO = new DistroVO();
     	jogoVO.setId(Integer.parseInt(jogoID));
     	vo.setDistro(jogoVO);
    	String dateStr = input.getString("dataLancamento");
    	Date dataLancamento = (Date) DateHelper.formatStrDate(dateStr);
     	vo.setDataLancamento(new java.sql.Date( dataLancamento.getTime()));

     	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar Versao: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover Versao: id="+vo.getId());
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("versoesDeDistros", listaDeVersaos());
		resetForm();
        return SUCCESS;
    }


    /**
     * action que remove um registro
     * @return
     * @throws Exception
     */
    public String remove() throws Exception {
        vo.setId( input.getInt("id"));
    	logger.info(" Remover Versao: id="+vo.getId());
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("versoesDeDistros", listaDeVersaos());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("id",new Integer(0));
		output.setValue("release","");
		output.setValue("dataLancamento","");
		output.setValue("link","");
		output.setValue("obs","");
		output.setValue("tipo_id",new Integer(0));
    }

    // getters e setters
	public VersaoDeDistroVO getVo() {
		return vo;
	}

	public void setVo(VersaoDeDistroVO vo) {
		this.vo = vo;
	}

	public Collection<?> getListaVO() {
		return listaVO;
	}

	public void setListaVO(Collection<?> listaVO) {
		this.listaVO = listaVO;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}


	public void prepareConverters(Map<String, Converter> converters, String innerAction) {

	 converters.put("dataLancamento", new DateConverter());

	}


}