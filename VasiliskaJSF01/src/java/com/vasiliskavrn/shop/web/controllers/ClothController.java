package com.vasiliskavrn.shop.web.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import com.vasiliskavrn.shop.web.db.DataHelper;
import com.vasiliskavrn.shop.web.entity.Cloth;

@ManagedBean(eager = false)
@ApplicationScoped
public class ClothController implements Serializable, Converter {

    private List<SelectItem> selectItems = new ArrayList<SelectItem>();
    private Map<Long, Cloth> clothMap;
    private List<Cloth> clothList;

    public ClothController() {

        clothMap = new HashMap<Long, Cloth>();
        clothList = DataHelper.getInstance().getAllCloths();

        for (Cloth cloth : clothList) {
            clothMap.put(cloth.getIdCloth(), cloth);
            selectItems.add(new SelectItem(cloth, cloth.getClothName()));
        }

    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    // 
    public List<Cloth> getClothList() {
        return clothList;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return clothMap.get(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Cloth) value).getIdCloth().toString();
    }
}
