package StateManager;

import drawSystem.*;

import java.awt.Color;

import Drawing.MyDrawing;

//ユーザーイベントの管理、現在のstateからcanvasにdrawする
//現在のstateは各ボタンからセットされる
public class StateManager {
	State state;
	MyCanvas canvas;
	Mediator mediator;

	boolean isDashed = false;
	boolean isShadow = false;
	boolean selectMode = false;
	int lineWidth = 1;
	int nLine = 1;
	Color lineColor = Color.black;
	Color fillColor = Color.white;
	Color shadowColor = Color.black;
	
	public StateManager(MyCanvas canvas){
		this.canvas = canvas;
		this.mediator = canvas.getMediator();
		setState(new TriangleButton(this).new TriangleState(this));
	}
	public void setState(State state){
		if(!selectMode)
			this.state = state;
	}
	
	public void setSelectMode(boolean selectMode) {
		this.selectMode = selectMode;
	}
	//<マウス処理>
	public void mouseDown(int x, int y) {
		state.mouseDown(x, y);
	}

	public void mouseDrag(int x, int y){
		state.mouseDrag(x, y);
	}

	public void mouseUp(int x, int y){
		state.mouseUp(x, y);
	}
	//</マウス処理>

	//現在のstateからcanvasに描画
	public void addDrawing(MyDrawing d) {
		d.setDashed(isDashed);
		d.setShadow(isShadow);
		d.setLineWidth(lineWidth);
		d.setNLine(nLine);
		d.setLineColor(lineColor);
		d.setFillColor(fillColor);
		d.setShadowColor(shadowColor);
		mediator.addDrawing(d);
		mediator.setSelectedDrawing(d);
	}

	//Decorateをつけずに追加
	public void addNoDecoratingDrawing(MyDrawing d) {
		mediator.addDrawing(d);
	}

	public void repaint() {
		canvas.repaint();
	}

	//<破線設定>
	public void setDash(boolean isDashed){
		this.isDashed = isDashed;
	}

	public boolean getDash(){
		return isDashed;
	}

	//影設定
	public void setShadow(boolean isShadow){
		this.isShadow = isShadow;
	}

	public boolean getShadow(){
		return isShadow;
	}

	//LineWidthの設定
	public void setLineWidth(int width){
		lineWidth = width;
	}

	public int getLineWidth(){
		return lineWidth;
	}

	//n重線の設定
	public void setNLine(int nLine){
		this.nLine = nLine;
	}

	public int getNLine() {
		return nLine;
	}

	//線の色の設定
	public void setLineColor(Color color) {
		this.lineColor = color;
	}
	
	public Color getLineColor() {
		return lineColor;
	}
	
	//塗りつぶし色の設定
	public void setFillColor(Color color) {
		this.fillColor = color;
	}
	
	public Color getFillColor(Color color) {
		return fillColor;
	}
	
	//影の色の設定
	public void setShadowColor(Color color) {
		this.shadowColor = color;
	}
	
	public Color getShadowColor() {
		return shadowColor;
	}
}
