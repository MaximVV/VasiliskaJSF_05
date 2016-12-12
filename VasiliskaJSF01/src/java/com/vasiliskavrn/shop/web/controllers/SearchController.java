package com.vasiliskavrn.shop.web.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import com.vasiliskavrn.shop.web.db.DataHelper;
import com.vasiliskavrn.shop.web.entity.Goods;
import com.vasiliskavrn.shop.web.enums.SearchType;

@ManagedBean(eager = true)
@SessionScoped
public class SearchController implements Serializable {

    private List<Goods> currentGoodsList; // текущий список книг для отображения
    private Long selectedAuthorId;// текущий автор книги из списка при редактировании книги
    private ArrayList<Integer> pageNumbers = new ArrayList<Integer>(); // кол-во страниц для постраничности
    // критерии поиска
    private char selectedLetter; // выбранная буква алфавита, по умолчанию не выбрана ни одна буква
    private SearchType selectedSearchType = SearchType.FOR_ALL;// хранит выбранный тип поиска, по-умолчанию - по названию
    private long selectedClothId; // выбранный жанр
    private String currentSearchString; // хранит поисковую строку
    // для постраничности----
    private boolean pageSelected;
    private int goodsCountOnPage = 2;// кол-во отображаемых книг на 1 странице
    private long selectedPageNumber = 1; // выбранный номер страницы в постраничной навигации
    private long totalGoodsCount; // общее кол-во книг (не на текущей странице, а всего), нажно для постраничности
    //-------
    private boolean editModeView;// отображение режима редактирования

    public SearchController() {
        fillGoodsAll();
    }

    private void submitValues(Character selectedLetter, long selectedPageNumber, long selectedClothId, boolean requestFromPager) {
        this.selectedLetter = selectedLetter;
        this.selectedPageNumber = selectedPageNumber;
        this.selectedClothId = selectedClothId;
        this.pageSelected = requestFromPager;

    }

    //<editor-fold defaultstate="collapsed" desc="запросы в базу">
    private void fillGoodsAll() {
        currentGoodsList = DataHelper.getInstance().getAllGoods();
    }

    public String fillGoodsByCloth() {

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        selectedClothId = Long.valueOf(params.get("idCloth"));

        submitValues(' ', 1, selectedClothId, false);
        currentGoodsList = DataHelper.getInstance().getGoodsByCloth(selectedClothId);

        return "goods";
    }
    
    public String fillGoodsByLetter() {
        
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        selectedLetter = params.get("letter").charAt(0);
        
        submitValues(selectedLetter, 1, -1, false);
        
        
       currentGoodsList = DataHelper.getInstance().getGoodsByLetter(selectedLetter);
        
        return "goods";
    }
    

    public String fillGoodsBySearch() {

        submitValues(' ', 1, -1, false);

        if (currentSearchString.trim().length() == 0) {
            fillGoodsAll();
            return "goods";
        }

        if (selectedSearchType == SearchType.FOR_ALL) {
            currentGoodsList = DataHelper.getInstance().getGoodsByName(currentSearchString);
        } else if (selectedSearchType == SearchType.FOR_MAN) {
            currentGoodsList = DataHelper.getInstance().getGoodsByName(currentSearchString);
        }


        return "goods";
    }

    public void updateGoods() {
       
        cancelEditMode();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="режим редактирования">
    public void showEdit() {
        editModeView = true;
    }

    public void cancelEditMode() {
        editModeView = false;
        for (Goods goods : currentGoodsList) {
            goods.setEdit(false);
        }
    }
    //</editor-fold>

    public Character[] getRussianLetters() {
        Character[] letters = new Character[]{'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я',};
        return letters;
    }

    public void searchStringChanged(ValueChangeEvent e) {
        currentSearchString = e.getNewValue().toString();
    }

    public void searchTypeChanged(ValueChangeEvent e) {
        selectedSearchType = (SearchType) e.getNewValue();
    }

    //<editor-fold defaultstate="collapsed" desc="постраничность">
    public void changeGoodsCountOnPage(ValueChangeEvent e) {
//        imitateLoading();
//        cancelEditMode();
//        pageSelected = false;
//        goodssCountOnPage = Integer.valueOf(e.getNewValue().toString()).intValue();
//        selectedPageNumber = 1;
//        fillGoodssBySQL(currentSqlNoLimit);
    }

    public void selectPage() {
//        cancelEditMode();
//        imitateLoading();
//        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        selectedPageNumber = Integer.valueOf(params.get("page_number"));
//        pageSelected = true;
//        fillGoodssBySQL(currentSqlNoLimit);
    }

    private void fillPageNumbers(long totalGoodsCount, int goodsCountOnPage) {

        int pageCount = 0;

        if (totalGoodsCount % goodsCountOnPage == 0) {
            pageCount = goodsCountOnPage > 0 ? (int) (totalGoodsCount / goodsCountOnPage) : 0;
        } else {
            pageCount = goodsCountOnPage > 0 ? (int) (totalGoodsCount / goodsCountOnPage) + 1 : 0;
        }

        pageNumbers.clear();
        for (int i = 1; i <= pageCount; i++) {
            pageNumbers.add(i);
        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="гетеры сетеры">
    public boolean isEditMode() {
        return editModeView;
    }

    public ArrayList<Integer> getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(ArrayList<Integer> pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public String getSearchString() {
        return currentSearchString;
    }

    public void setSearchString(String searchString) {
        this.currentSearchString = searchString;
    }

    public SearchType getSearchType() {
        return selectedSearchType;
    }

    public void setSearchType(SearchType searchType) {
        this.selectedSearchType = searchType;
    }

    public List<Goods> getCurrentGoodsList() {
        return currentGoodsList;
    }

    public void setTotalGoodsCount(long goodsCount) {
        this.totalGoodsCount = goodsCount;
    }

    public long getTotalGoodsCount() {
        return totalGoodsCount;
    }

    public long getSelectedClothId() {
        return selectedClothId;
    }

    public void setSelectedClothId(Long selectedClothId) {
        this.selectedClothId = selectedClothId;
    }

    public char getSelectedLetter() {
        return selectedLetter;
    }

    public void setSelectedLetter(char selectedLetter) {
        this.selectedLetter = selectedLetter;
    }

    public int getGoodsOnPage() {
        return goodsCountOnPage;
    }

    public void setGoodsOnPage(int goodsOnPage) {
        this.goodsCountOnPage = goodsOnPage;
    }

    public void setSelectedPageNumber(long selectedPageNumber) {
        this.selectedPageNumber = selectedPageNumber;
    }

    public long getSelectedPageNumber() {
        return selectedPageNumber;
    }

    public Long getSelectedAuthorId() {
        return selectedAuthorId;
    }

    public void setSelectedAuthorId(Long selectedAuthorId) {
        this.selectedAuthorId = selectedAuthorId;
    }
    
    
    
    
}
