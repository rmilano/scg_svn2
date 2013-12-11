/* 
 * Muestra el arbol de areas
 * 
 */


var parentId = "", lastClickedIdArea = 0;
/*
 * Se crea la configuracion global para los arboles
 *
 */
$(function(){
    /* Agregar imagen referente al arbol de areas*/
    $("input.tree").after("<img class='icon-tree-trigger' src='../../imagenes/arbol/icontree.png' alt='' title='Seleccione un &aacute;rea'>");
    /* configura el arbol*/
    $("#tree").treeview({
        collapsed: true,
        animated: "medium",
        control:"#sidetreecontrol",
        persist: "location"
    });
    $(".tree-branch").treeview({
        collapsed: true,
        animated: "medium",
        control:"#sidetreecontrol",
        persist: "location"
    });

    /* Crea los dialogos*/
    $(".modal").dialog( {
        autoOpen : false,
        modal : true,
        buttons : {
            'Aceptar' : function() {
                $(this).dialog('close');
            }
        },
        Cerrar : function() {
            $(this).dialog('close');
        },
        width: 300,
        height: 350
    });

    $(".modal-rama").dialog( {
        autoOpen : false,
        modal : true,
        buttons : {
            'Aceptar' : function() {
                $(this).dialog('close');
            }
        },
        Cerrar : function() {
            $(this).dialog('close');
        },
        width: 300,
        height: 350
    });
    $(".modal-arbol-externo").dialog( {
        autoOpen : false,
        modal : true,
        buttons : {
            'Aceptar' : function() {
                $(this).dialog('close');
            }
        },
        Cerrar : function() {
            $(this).dialog('close');
        },
        width: 300,
        height: 350
    });

    $('.treeview .hitarea').live('click', function() {
        var idArea = $(this).next().attr("id");
        if($(this).hasClass("expandable-hitarea"))
        {
            $("ul.add_"+idArea).remove();
        }
        if($(this).hasClass("collapsable-hitarea"))//expande el cuadro
        {            
            addChildToParentArea(idArea, $(this).parent(), "tree");
        }
    });
});

/*
 * Agregar los hijos al area padre correpondiente
 */
function addChildToParentArea(idArea, parent, idArbol)
{
    var url = "../../areas.do?method=getAreasByParent",
    dinamicTree = "", branches = null;//expandable-hitarea
    $.post(url,
    {
        idArea: idArea
    }, function(data){
        if(data != "error")
        {
            $.each(data, function(key, data){
                dinamicTree = dinamicTree + "<li class='closed' id=''><a id='" + 
                data.id_area + "'>" + data.area + "</a><ul><li><a></a></li></ul><li>";
            });
            branches = $("<ul class='add_" + idArea + "'>" + dinamicTree + "</ul>").appendTo(parent);
            $("#" + idArbol).treeview({
                add: branches
            });

        }
        else
            jAlert("Ha ocurrido un erro al obtener las areas.", "Error al obtener las areas");
    }, "json");
}

/*
 * Se hará la carga de empleados
 * 
 */
function loadEmployed(placeLoad, idArea)
{
    var url = "../../areas.do?method=getEmpleadosByArea",
    i = 0,
    empty = "<option value=''>No se encontraron registros</option>",
    dinamicOptionFromSelect = "";
    var dinamicOptionFromSelect2 = "";
    var puestos;
    puestos="";
    $.post(url,{
        idArea: idArea
    }, function(data){
            $.each(data, function(key, data) {
            dinamicOptionFromSelect = dinamicOptionFromSelect + "<option value='" + data.id_empleado + "'>"
            + data.nombre_completo + "</option>";
            dinamicOptionFromSelect2 = dinamicOptionFromSelect2 + "<option value='" + data.id_empleado + "'>"
            + data.puesto + "</option>";
            puestos=puestos+","+data.id_empleado+"-"+data.puesto;
            i++;
        });
        if(!i){
            dinamicOptionFromSelect  = empty;
            dinamicOptionFromSelect2  = empty;
        }
        $(placeLoad).html(dinamicOptionFromSelect);
        $("#puesto_dest").html(dinamicOptionFromSelect2);
        $("#puestos").val(puestos.substring(1));
    }, "json");
}

/*
 * Carga de empleados ordenados por uso
 *
 */
function loadEmployed(placeLoad, idArea,origen)
{
    var url = "../../areas.do?method=getEmpleadosByArea",
    i = 0,
    empty = "<option value=''>No se encontraron registros</option>",
    dinamicOptionFromSelect = "";
    var dinamicOptionFromSelect2 = "";
    var puestos;
    puestos="";
    $.post(url,{
        idArea: idArea,
        origen: origen
    }, function(data){
            $.each(data, function(key, data) {
            dinamicOptionFromSelect = dinamicOptionFromSelect + "<option value='" + data.id_empleado + "'>"
            + data.nombre_completo + "</option>";
            dinamicOptionFromSelect2 = dinamicOptionFromSelect2 + "<option value='" + data.id_empleado + "'>"
            + data.puesto + "</option>";
            puestos=puestos+",-"+data.id_empleado+"-"+data.puesto;
            i++;
        });
        if(!i){
            dinamicOptionFromSelect  = empty;
            dinamicOptionFromSelect2  = empty;
        }
        $(placeLoad).html(dinamicOptionFromSelect);
        $("#puesto_dest").html(dinamicOptionFromSelect2);
        if(placeLoad.indexOf("dest")>0||placeLoad.indexOf("arbolE")>0){
            $("#puestos_dest").val(puestos.substring(1));
        }
        else if(placeLoad.indexOf("remi")>0){
            $("#puestos_remi").val(puestos.substring(1));
        }
        $(placeLoad).trigger('change');
    }, "json");
}

/*
 * Crea el arbol
 */
function crearArbol(option)
{
    // Dispara el evento
    $(".icon-tree-trigger").click(function(){
        parentId = $(this).parent().attr("id");        
        var associatedTreeName = $("#" + parentId).attr("title");
        if(associatedTreeName == null || associatedTreeName == '') associatedTreeName = 'modal';
        $(".treeviewContent a#"+lastClickedIdArea).prev().click();//expandir o no el arbol para que recargue el area recientemente registrada
        $('.' + associatedTreeName).dialog('open');//abrir el dialogo
    });
    //Asigna las áreas y los empleados respectivamente
    $('.treeviewContent a').live('click', function() {
        var idArea = $(this).attr("id");
        lastClickedIdArea = idArea; //Se guarda el ultimo
        var nombreArea = $(this).text();
        $("#" + parentId + " input.tree").attr("value", nombreArea);
        $("#" + parentId + " .id").attr("value", idArea);
        if(option == "empleado")
        {
            loadEmployed("#" + parentId + " select", idArea,"rede");
            $("#select-dest").change();
            $("#select-dest").trigger('change');
        }
    });
}
/*
 *    crear una funcion generica para configurar el arbol
 */
(function($)
{
    $.fn.parentId = $(this).attr("id");

    // $.fn is the object we add our custom functions to
    $.fn.configureTree = function(data)
    {
        return this.each(function(){
            // Dispara el evento
            $(this + " .icon-tree-trigger").click(function(){
                var associatedTreeName = $(this).attr("title");
                if(associatedTreeName == null || associatedTreeName == '') associatedTreeName = 'modal';
                $('.' + associatedTreeName).dialog('open');//abrir el dialogo
            });
            //Asigna las áreas y los empleados respectivamente
            $(this.parentId + " .treeviewContent a").click(function(){
                var idArea = $(this).attr("id");
                var nombreArea = $(this).text();
                $(this + " input.tree").attr("value", nombreArea);
                $(this + " .id").attr("value", idArea);
                if(data == "empleado")
                {
                    loadEmployed(this + " select", idArea);
                    $("#select-dest").change();
                    $("#select-dest").trigger('change');
                }
            });
        });
    };
})(jQuery);


function removeItemFromEmpleadoDest()
{
    var idEmpleadoSession = $("#id_empleado_session").val();
    $("#select-dest option[value="+idEmpleadoSession+"]").remove();
}