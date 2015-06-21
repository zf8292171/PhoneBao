package geniuz.pathbuttons;

import geniuz.myPathbutton.composerLayout;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ���ÿؼ�
		composerLayout clayout = (composerLayout) findViewById(R.id.test);
		clayout.init(new int[] { R.drawable.composer_camera,
				R.drawable.composer_music, R.drawable.composer_place,
				R.drawable.composer_sleep}, R.drawable.composer_button,
				R.drawable.composer_icn_plus, composerLayout.LEFTBOTTOM, 180,
				300);
		// �ӂ��c���O ��100+0����composer_camera��100+1����composer_music�������������Ў׶������o�ͼӎ׶�����
		OnClickListener clickit = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == 100 + 0) {
					System.out.println("composer_camera");
				} else if (v.getId() == 100 + 1) {
					System.out.println("composer_music");
				} else if (v.getId() == 100 + 2) {
					System.out.println("composer_place");
				} else if (v.getId() == 100 + 3) {
					System.out.println("composer_sleep");
				} else if (v.getId() == 100 + 4) {
					System.out.println("composer_thought");
				} else if (v.getId() == 100 + 5) {
					System.out.println("composer_with");
				}
			}
		};
		clayout.setButtonsOnClickListener(clickit);

		// �����؎׾伃��{���yԇ�¸��ؼ��c���c�������H�Æ��r�����ȥ����
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.rlparent);
		rl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("���ؼ������c���ͼ�ϵ�����؅���");
			}
		});

	}

}
