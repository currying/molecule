package com.toparchy.push.utils;

import com.toparchy.push.nio.ByteBuf;
import com.toparchy.push.nio.CompositByteBuf;
import com.toparchy.push.nio.SimpleByteBuf;


public  class ByteBufUtil {


	public static ByteBuf ByteBuf() {
		return new SimpleByteBuf(ByteBuf.BUFFER_SIZE);
	}
	


	public static ByteBuf compositBuf() {
		return new CompositByteBuf();
	}
	
	
	
	public static ByteBuf wrapByteBuf(ByteBuf buf) {
		return new CompositByteBuf(buf);
	}

	
	
	public static ByteBuf wrapByteBuf(int byteLength,ByteBuf buf) {
		int sizeToExtend = 0 ;
		if(byteLength % ByteBuf.BUFFER_SIZE == 0){
			sizeToExtend = byteLength / ByteBuf.BUFFER_SIZE;
		}else{
			sizeToExtend = (byteLength / ByteBuf.BUFFER_SIZE) + 1;
		}
		
		return new CompositByteBuf(sizeToExtend + 1,buf);
	}

}
