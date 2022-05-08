package com.aidex.web.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.Collection;
import java.util.List;

public class PageInfoUtil <T> extends com.github.pagehelper.PageSerializable<T> {
    public static final int DEFAULT_NAVIGATE_PAGES = 8;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //当前页的数量
    private int size;
    //总页数
    private int pages;

    //由于startRow和endRow不常用，这里说个具体的用法
    //可以在页面中"显示startRow到endRow 共size条数据"

//    //当前页面第一个元素在数据库中的行号
//    private long startRow;
//    //当前页面最后一个元素在数据库中的行号
//    private long endRow;


//    //前一页
//    private int prePage;
//    //下一页
//    private int nextPage;

//    //是否为第一页
//    private boolean isFirstPage = false;
//    //是否为最后一页
//    private boolean isLastPage = false;
//    //是否有前一页
//    private boolean hasPreviousPage = false;
//    //是否有下一页
//    private boolean hasNextPage = false;
//    //导航页码数
//    private int navigatePages;
//    //所有导航页号
//    private int[] navigatepageNums;
//    //导航条上的第一页
//    private int navigateFirstPage;
//    //导航条上的最后一页
//    private int navigateLastPage;

    public PageInfoUtil() {
    }

    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageInfoUtil(List<T> list) {
        this(list, DEFAULT_NAVIGATE_PAGES);
    }

    /**
     * 包装Page对象
     *
     * @param list          page结果
     * @param navigatePages 页码数量
     */
    public PageInfoUtil(List<T> list, int navigatePages) {
        super(list);
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();

            this.pages = page.getPages();
            this.size = page.size();
            //由于结果是>startRow的，所以实际的需要+1
//            if (this.size == 0) {
//                this.startRow = 0;
//                this.endRow = 0;
//            } else {
//                this.startRow = page.getStartRow() + 1;
//                //计算实际的endRow（最后一页的时候特殊）
//                this.endRow = this.startRow - 1 + this.size;
//            }
        } else if (list instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();

            this.pages = this.pageSize > 0 ? 1 : 0;
            this.size = list.size();
//            this.startRow = 0;
//            this.endRow = list.size() > 0 ? list.size() - 1 : 0;
        }
//        if (list instanceof Collection) {
//            calcByNavigatePages(navigatePages);
//        }
    }

    public static <T> PageInfo<T> of(List<T> list) {
        return new PageInfo<T>(list);
    }

    public static <T> PageInfo<T> of(List<T> list, int navigatePages) {
        return new PageInfo<T>(list, navigatePages);
    }

//    public void calcByNavigatePages(int navigatePages) {
//        setNavigatePages(navigatePages);
//        //计算导航页
////        calcNavigatepageNums();
//        //计算前后页，第一页，最后一页
//        calcPage();
//        //判断页面边界
//        judgePageBoudary();
//    }

//    /**
//     * 计算导航页
//     */
//    private void calcNavigatepageNums() {
//        //当总页数小于或等于导航页码数时
//        if (pages <= navigatePages) {
//            navigatepageNums = new int[pages];
//            for (int i = 0; i < pages; i++) {
//                navigatepageNums[i] = i + 1;
//            }
//        } else { //当总页数大于导航页码数时
//            navigatepageNums = new int[navigatePages];
//            int startNum = pageNum - navigatePages / 2;
//            int endNum = pageNum + navigatePages / 2;
//
//            if (startNum < 1) {
//                startNum = 1;
//                //(最前navigatePages页
//                for (int i = 0; i < navigatePages; i++) {
//                    navigatepageNums[i] = startNum++;
//                }
//            } else if (endNum > pages) {
//                endNum = pages;
//                //最后navigatePages页
//                for (int i = navigatePages - 1; i >= 0; i--) {
//                    navigatepageNums[i] = endNum--;
//                }
//            } else {
//                //所有中间页
//                for (int i = 0; i < navigatePages; i++) {
//                    navigatepageNums[i] = startNum++;
//                }
//            }
//        }
//    }

//    /**
//     * 计算前后页，第一页，最后一页
//     */
//    private void calcPage() {
//        if (navigatepageNums != null && navigatepageNums.length > 0) {
//            navigateFirstPage = navigatepageNums[0];
//            navigateLastPage = navigatepageNums[navigatepageNums.length - 1];
//            if (pageNum > 1) {
//                prePage = pageNum - 1;
//            }
//            if (pageNum < pages) {
//                nextPage = pageNum + 1;
//            }
//        }
//    }

//    /**
//     * 判定页面边界
//     */
//    private void judgePageBoudary() {
//        isFirstPage = pageNum == 1;
//        isLastPage = pageNum == pages || pages == 0;
//        hasPreviousPage = pageNum > 1;
//        hasNextPage = pageNum < pages;
//    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

//    public long getStartRow() {
//        return startRow;
//    }
//
//    public void setStartRow(long startRow) {
//        this.startRow = startRow;
//    }
//
//    public long getEndRow() {
//        return endRow;
//    }
//
//    public void setEndRow(long endRow) {
//        this.endRow = endRow;
//    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

//    public int getPrePage() {
//        return prePage;
//    }
//
//    public void setPrePage(int prePage) {
//        this.prePage = prePage;
//    }
//
//    public int getNextPage() {
//        return nextPage;
//    }
//
//    public void setNextPage(int nextPage) {
//        this.nextPage = nextPage;
//    }
//
//    public boolean isIsFirstPage() {
//        return isFirstPage;
//    }
//
//    public void setIsFirstPage(boolean isFirstPage) {
//        this.isFirstPage = isFirstPage;
//    }
//
//    public boolean isIsLastPage() {
//        return isLastPage;
//    }
//
//    public void setIsLastPage(boolean isLastPage) {
//        this.isLastPage = isLastPage;
//    }
//
//    public boolean isHasPreviousPage() {
//        return hasPreviousPage;
//    }
//
//    public void setHasPreviousPage(boolean hasPreviousPage) {
//        this.hasPreviousPage = hasPreviousPage;
//    }
//
//    public boolean isHasNextPage() {
//        return hasNextPage;
//    }
//
//    public void setHasNextPage(boolean hasNextPage) {
//        this.hasNextPage = hasNextPage;
//    }
//
//    public int getNavigatePages() {
//        return navigatePages;
//    }
//
//    public void setNavigatePages(int navigatePages) {
//        this.navigatePages = navigatePages;
//    }
//
//    public int[] getNavigatepageNums() {
//        return navigatepageNums;
//    }
//
//    public void setNavigatepageNums(int[] navigatepageNums) {
//        this.navigatepageNums = navigatepageNums;
//    }
//
//    public int getNavigateFirstPage() {
//        return navigateFirstPage;
//    }
//
//    public int getNavigateLastPage() {
//        return navigateLastPage;
//    }
//
//    public void setNavigateFirstPage(int navigateFirstPage) {
//        this.navigateFirstPage = navigateFirstPage;
//    }
//
//    public void setNavigateLastPage(int navigateLastPage) {
//        this.navigateLastPage = navigateLastPage;
//    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageInfo{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", size=").append(size);
//        sb.append(", startRow=").append(startRow);
//        sb.append(", endRow=").append(endRow);
        sb.append(", total=").append(total);
        sb.append(", pages=").append(pages);
        sb.append(", list=").append(list);
//        sb.append(", prePage=").append(prePage);
//        sb.append(", nextPage=").append(nextPage);
//        sb.append(", isFirstPage=").append(isFirstPage);
//        sb.append(", isLastPage=").append(isLastPage);
//        sb.append(", hasPreviousPage=").append(hasPreviousPage);
//        sb.append(", hasNextPage=").append(hasNextPage);
//        sb.append(", navigatePages=").append(navigatePages);
//        sb.append(", navigateFirstPage=").append(navigateFirstPage);
//        sb.append(", navigateLastPage=").append(navigateLastPage);
        sb.append(", navigatepageNums=");
//        if (navigatepageNums == null) {
//            sb.append("null");
//        } else {
//            sb.append('[');
//            for (int i = 0; i < navigatepageNums.length; ++i) {
//                sb.append(i == 0 ? "" : ", ").append(navigatepageNums[i]);
//            }
//            sb.append(']');
//        }
        sb.append('}');
        return sb.toString();
    }
}
