package fr.harmonicate.essence;

import java.util.Calendar;
import java.util.Locale;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	ConnexionBDD connexionBDD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		connexionBDD = new ConnexionBDD(this);
		
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		
		Button buttonOK = (Button) findViewById(R.id.button1);
		if(buttonOK != null) {
			buttonOK.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					validateAndSendData();
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new EssenceSectionFragment();
			Bundle args = new Bundle();
			args.putInt(EssenceSectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			}
			
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class EssenceSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		
		public EssenceSectionFragment(){
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			int position=getArguments().getInt(ARG_SECTION_NUMBER);
			View rootView;
			if (position==1)
			{
				 rootView= inflater.inflate(R.layout.vue_essence,
						container, false);
			}
			else 
			{
				rootView = inflater.inflate(R.layout.vue_essence_2,
						container, false);
			}
			
			return rootView;
		}
	}

	public static class DatePickerFragment extends DialogFragment implements OnDateSetListener
    {
		@Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the current date as the default date in the picker
	        final Calendar c = Calendar.getInstance();
	        int year = c.get(Calendar.YEAR);
	        int month = c.get(Calendar.MONTH);
	        int day = c.get(Calendar.DAY_OF_MONTH);
	        // Create a new instance of DatePickerDialog and return it
	        return new DatePickerDialog(getActivity(), this , year, month, day);
	    }


		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			EditText dateEditText = (EditText) getActivity().findViewById(R.id.editText2);
			
			String date= "";
			if(dayOfMonth < 10){
				date += "0";
			}
			date += "" +dayOfMonth + "/";
			
			if(monthOfYear < 10){
				date += "0";
			}
			date += "" + (monthOfYear+1) + "/" + year;
			
			dateEditText.setText(date);
			
		}
		
	}
	
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}

	private void validateAndSendData(){
		if(validateDate()){
			Plein plein=new Plein();
			
			String date = ((EditText) findViewById(R.id.editText2)).getText().toString();
			
			RadioButton radioButton = (RadioButton) findViewById(R.id.radioButton1);
			String nomPersonne;
			if(radioButton.isChecked())
				nomPersonne = radioButton.getText().toString();
			else {
				radioButton = (RadioButton) findViewById(R.id.radioButton2);
				nomPersonne = radioButton.getText().toString();
			}
			
			EditText prix = (EditText) findViewById(R.id.editText1);
			String prixLitre = prix.getText().toString();
			
			plein.setDatePlein(date);
			plein.setNomPersonne(nomPersonne);
			plein.setPrixLitre(prixLitre);
			
			connexionBDD.addPlein(plein);
		}
	}


	private boolean validateDate() {
		String date = ((EditText) findViewById(R.id.editText2)).getText().toString();
		String[] split = date.split("/");
		//vérification jour
		if(split.length != 3){
			return false;
		}
		int day = Integer.parseInt(split[0]);
		if(day < 1 || day > 31){
			return false;
		}
		int month = Integer.parseInt(split[1]);
		if(month < 1 || month > 12){
			return false;
		}
		int year = Integer.parseInt(split[2]);
		if(year < Calendar.getInstance().get(Calendar.MONTH)){
			return false;
		}
		return true;
	}

}
