package org.bdqn.firstwork.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PaginationDTO<T> {

	
	private List<T> data;
	private Integer curPage;
	private Integer totalCount;
	private Integer totalPage;
	private boolean hasNext;
	private boolean hasPreious;
	private boolean hasLastPage;
	private boolean hasFirstPage;
	private Integer size;
	private List<Integer> pages = new ArrayList<Integer>();;
	
	//param(当前页，页长度，总数据) 当前页 并计算页数
	public void oprData(Integer curPage,Integer size,Integer totalCount) {
		this.size = size ; 
		this.totalCount = totalCount;
		totalPage = totalCount % size == 0 ? totalCount / size : (totalCount / size) + 1;
		this.curPage = curPage>=totalPage?totalPage:curPage;
		this.curPage = curPage<=0?1:this.curPage;
		
		//1    1234
		//2    12345
		//3    123456
		//4    1234567
		//5    2345678
		//6    3456789

		
		
		if(curPage>1) {
			hasPreious=true;
		}
		if(curPage<totalPage) {
			hasNext=true;
		}
		
		
		pages.add(curPage);
		for (int i = 1; i <= 3 ; i++) {
			if(curPage-i>0) {
				pages.add(0, curPage-i);
			}
			if(curPage+i<=totalPage) {
				pages.add(curPage+i);
			}
		}
		
		if(pages.get(0)!=1) {
			hasFirstPage = true ;
		}
		if(pages.get(pages.size()-1)!=totalPage) {
			hasLastPage = true;
		}
		
		
	}
	
	
}
