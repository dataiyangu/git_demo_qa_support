package com.ilucky.ejb;

import java.util.Arrays;

public class RemoveAtTest {

	/**
	 * 注意: removeIndex和putIndex的关系有两种情况, 一种是38, 另一种是03.
	 * 如果要删除一个元素, 那么需要向前推移removeIndex到putIndex所有元素.
	 * @param args
	 */
	public static void main(String[] args) {
		String[] items= new String[]{"0", "1", "2", null, null, null, "7", "8", "9"};
		 int putIndex = 3;
		 int removeIndex = 0; //0 , 8
		 
		 // 从removeIndex开始遍历items.
         for (int i = removeIndex;;) {
             int next = i + 1;
             
             // 如果next等于items.length，即完成了removeIndex到items.length之间元素的推移.
             if (next == items.length)
                 next = 0;
            
             // 如果当前元素的next不等于putIndex, 则将当前元素置为下一个元素， 即向前推移.
             if (next != putIndex) {
                 items[i] = items[next];
                 i = next;
             
             // 如果当前元素的next等于putIndex, 则将当前元素置为null(因为已经推移到上个坐标了), 同时修改putIndex.
             } else {
                 items[i] = null;
                 putIndex = i;
                 break;
             }
         }
         System.out.println(Arrays.toString(items));
	}
}
/**
int removeIndex = 0; 
[1, 2, null, null, null, null, null, 7, 8, 9]
int removeIndex = 8; 
[1, 2, null, null, null, null, null, 7, 9, 0]
*/