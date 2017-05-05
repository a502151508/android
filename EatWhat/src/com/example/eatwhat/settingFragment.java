package com.example.eatwhat;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class settingFragment extends Fragment implements OnClickListener {
	Button btnSet,btnItr;
	CheckBox cb;
	TextView outtv;
	/**
	 * @param args
	 */
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.frame_setting,container);
	}
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		btnSet=(Button)getView().findViewById(R.id.btn_set);
		btnItr=(Button)getView().findViewById(R.id.btn_intro);
		btnSet.setOnClickListener(this);
		btnItr.setOnClickListener(this);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void onClick(View btn) {
		if(btn.getId()==R.id.btn_set)
		{
		Intent it = new Intent(getActivity(),SettingActivity.class);
		startActivityForResult(it,1);
		}
		if(btn.getId()==R.id.btn_intro){
			outtv=(TextView)getView().findViewById(R.id.TI);
			outtv.setText("����Ϊ�˽��ÿ��Ĵ��£����׳�ʲô���ĳ��ԣ����������APP�ĵ�������������һ�¹���  ��ҳ����ѡ���������Ե���𲢵����ť�������ѡ����������Ĳ�����" +
			"�ڹ���ҳ����ԶԲ���������б༭ ��������ҳ������ԶԲ�����������ƽ����Զ��塣�����ª������û����ƸУ����ܴպ�~");
		}

}
}
