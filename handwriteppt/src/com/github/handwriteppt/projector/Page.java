package com.github.handwriteppt.projector;

import java.util.ArrayList;
import java.util.List;

public class Page {
	
	private final int number;
	
	private List<Layer> layers=new ArrayList<Layer>();
	
	
	public Page(int number) {
		super();
		this.number = number;
	}


	public int getNumber(){
		return number; 
	}
	
	public void addLayer(Layer layer){
		layers.add(layer);
	}
	
	public int getLayerCount(){
		return layers.size();
	}
	
	public Layer getLayer(int index){
	return	layers.get(index-1);
	}
	
	public List<Layer> getAllLayers(){
		return layers;
	}

}
