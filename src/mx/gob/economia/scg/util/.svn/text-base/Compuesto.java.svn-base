package mx.gob.economia.scg.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Compuesto extends Componente
{

    private List<Componente> hijo = new ArrayList<Componente>();

    public Compuesto(String name, Integer id)
    {
        super(name, id);
    }

    @Override
    public void agregar(Componente componente)
    {
        this.hijo.add(componente);
    }

    @Override
    public void remover(Componente componente)
    {
        this.hijo.remove(componente);
    }
    /* El arte del buen comer ;).
     * @param profundidad. EL tag html que sera empleado para las vi�etas
     * return String : el arbol construido tras la iteraci�n
     */

    @Override
    public String mostrar(String profundidad)
    {
        profundidad = profundidad + "<a id=\"" + this.getId() + "\" >" + this.getNombre() + " </a>";
        //si no existen mas hijos, entonces cerrar el tag
        if (this.hijo.isEmpty())
        {
            profundidad = profundidad + "</li>";
            return profundidad;
        } else//de lo contrario abrir subarbol
        {
            profundidad = profundidad + "<ul>";
        }
        /*Iniciar el barrido del arbol*/
        for (Iterator<Componente> it = this.hijo.iterator(); it.hasNext();)
        {
            Componente componente = it.next();
            profundidad = profundidad + componente.mostrar("<li>");
        }
        profundidad = profundidad + "</ul></li>";
        return profundidad;
    }
}
