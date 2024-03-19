import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { UserService } from './user.service';

@Component({
  selector: 'app-user-input',
  templateUrl: './user-input.component.html',
  styleUrls: ['./user-input.component.css'],
})
export class UserInputComponent implements OnInit {
  public formUser: FormGroup;
  constructor(
    private fb: FormBuilder,
    private toast: ToastrService,
    private userService: UserService
  ) {
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

  onSubmit() {
    if (this.formUser.valid) {
      this.userService.addUser(this.formUser.value).subscribe(
        () => {
          this.toast.success('Usuário adicionado com sucesso!');
          this.formUser.reset();
        },
        (error: any) => {
          this.toast.error('Erro ao adicionar usuário.');
          console.error(error);
        }
      );
    }
  }
}
