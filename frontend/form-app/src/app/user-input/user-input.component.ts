import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-user-input',
  templateUrl: './user-input.component.html',
  styleUrls: ['./user-input.component.css'],
})
export class UserInputComponent implements OnInit {
  public formUser: FormGroup;
  constructor(private fb: FormBuilder, private toast: ToastrService) {
    this.formUser = this.createFormUser();
  }

  ngOnInit(): void {}

  public createFormUser(): FormGroup {
    return this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  public isFormControlInvalid(controlName: string): boolean {
    const control = this.formUser.get(controlName);
    return !!(control && control.invalid && (control.touched || control.dirty));
  }
}
