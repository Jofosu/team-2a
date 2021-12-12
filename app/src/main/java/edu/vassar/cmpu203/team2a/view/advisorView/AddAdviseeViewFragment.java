package edu.vassar.cmpu203.team2a.view.advisorView;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.team2a.controller.ControllerActivity;
import edu.vassar.cmpu203.team2a.databinding.FragmentAddAdviseeBinding;
import edu.vassar.cmpu203.team2a.view.advisorView.UserInputValidator;

/**
 *
 */
public class AddAdviseeViewFragment extends Fragment implements IManageAdviseeView {
    private final int freshYear;
    private final int seniorYear;
    private FragmentAddAdviseeBinding binding;
    private final Listener listener;

    public AddAdviseeViewFragment(Listener listener){
        this.listener = listener;
        this.freshYear = 2025;
        this.seniorYear = 2022;
    }

    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentAddAdviseeBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    /**
     * @param view
     * @param savedInstanceState
     */
    //implemented from IManageAdvisee
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){



            this.binding.endButton.setOnClickListener((clickedView) -> {
                //First Name of Advisee
            Editable firstNameEditable = binding.editFirstName.getText();
            String firstname = firstNameEditable.toString();
                if(!UserInputValidator.isFirstName(firstname)) Snackbar.make(view, "Invalid first name. Please provide a valid first name.",Snackbar.LENGTH_LONG).show();

                //Middle Name of Advisee
            Editable middleNameEditable = binding.editMiddleName.getText();
            String middleName = middleNameEditable.toString();
                if(!UserInputValidator.isMiddleName(middleName)) Snackbar.make(view, "Invalid middle name. Please provide a valid middle name.",Snackbar.LENGTH_LONG).show();


                //Last Name of Advisee
            Editable lastNameEditable = binding.editLastName.getText();
            String lastName = lastNameEditable.toString();
                if(!UserInputValidator.isLastName(lastName)) Snackbar.make(view, "Invalid last name. Please provide a valid last name.",Snackbar.LENGTH_LONG).show();
                boolean validName = UserInputValidator.isFirstName(firstname) && UserInputValidator.isLastName(lastName) && UserInputValidator.isMiddleName(middleName);
            String fullName = firstname+ " " + middleName+ " " + lastName;

            // Class Year of Advisee
            Editable classYearEditable = binding.editClassYear.getText();
            String classYear = classYearEditable.toString();


            if(!UserInputValidator.isValidClassYear(classYear)) Snackbar.make(view, "Invalid class Year. Please provide a current class year in the pattern 20XX.",Snackbar.LENGTH_LONG).show();
            boolean validClassYear = UserInputValidator.isValidClassYear(classYear);


            Editable idEditable = binding.editStudentId.getText();
            String idString = idEditable.toString();

            if(!UserInputValidator.isValidStudentId(idString)) Snackbar.make(view, "Please type valid student id", Snackbar.LENGTH_LONG).show();
            boolean validId = UserInputValidator.isValidStudentId(idString);

                ControllerActivity activity = (ControllerActivity) getActivity();
                if(activity.getAdvisor().getAdvisee(Integer.parseInt(idString)) != null){ Snackbar.make(view, "Student id already exists. Please type valid student id", Snackbar.LENGTH_LONG).show();
                validId = false;
                }
            if(validClassYear && validName&&validId)this.listener.addAdvisee(fullName, Integer.parseInt(idString), Integer.parseInt(classYear));
        });




    }




}