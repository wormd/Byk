import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../_service/auth.service';
import {AlertService} from '../../_service/alert.service';

@Component({
  selector: 'app-signup-form-page',
  templateUrl: './signup-form-page.component.html',
  styleUrls: ['./signup-form-page.component.css']
})
export class SignupFormPageComponent implements OnInit {

  signupForm: FormGroup;
  loading = false;
  submitted = false;
  error = false;
  errormsg: string;

  constructor(private route: ActivatedRoute, private router: Router,
              private formBuilder: FormBuilder,
              private authService: AuthService,
              public alertService: AlertService,
              ) { }

  ngOnInit(): void {
    if (this.authService.loggedIn()) {
      this.router.navigate(['/']);
    }
    this.signupForm = this.formBuilder.group({
      user: ['', Validators.required],
      pwd: ['', Validators.required]
    });
  }

  get f() {
    return this.signupForm.controls;
  }

  onSubmit() {
    this.loading = true;
    this.authService.signup(this.f.user.value, this.f.pwd.value).subscribe(() => {
      this.alertService.message('Sign up was successful', 'success');
      this.submitted = true;
      this.router.navigate(['/']);
      location.reload();
    }, e => {
      this.loading = false;
      console.log(e)
      if (e.error.message) {
        this.alertService.message(e.error.message, 'danger');
      } else {
        this.alertService.message('Signup failed for unknown reasons', 'danger');
      }
    });
  }

}
