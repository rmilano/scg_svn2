/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.economia.scg.persistence;

import java.util.List;

import mx.gob.economia.scg.model.RolDocto;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 *
 * @author gerardo
 */
public class RolDoctoDaoImpl extends SqlMapClientTemplate implements RolDoctoDao
{

    public List<RolDocto> listRoles()
    {
        List<RolDocto> roles = this.queryForList("RolDocto.listRoles");
        return roles;
    }
}
