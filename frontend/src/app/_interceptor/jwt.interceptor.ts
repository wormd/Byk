import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from '../_service/auth.service';
import {tap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {AlertService} from '../_service/alert.service';
import { environment } from 'src/environments/environment';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private router: Router, private authService: AuthService, private alertService: AlertService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = localStorage.getItem('token');
    if (request.body && !environment.production) {
      console.log("Request body:" + JSON.stringify(request.body));
    }
    if (token) {
      request = request.clone( {setHeaders: {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json'
      }});
    }
    //  else {
    //   // redirect to login if not logged
    //   this.router.navigate(['login']);
    // }
    if (request instanceof HttpResponse && !environment.production) {
      console.log("Reponse body: " + request.body)
    }
    return next.handle(request).pipe(tap(()  => {}, err => {
      if (err instanceof HttpErrorResponse && err.status === 401) {
        localStorage.removeItem('token');
        // this.router.navigate(['/login']);
      } else {
        this.alertService.message(`Code: ${err.status}, Message: ${err.message}`, 'danger');
      }
    }));
  }
}
