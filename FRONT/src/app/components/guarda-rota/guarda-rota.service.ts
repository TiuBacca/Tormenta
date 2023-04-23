import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GuardaRotaService implements CanActivate  {

  constructor(private router: Router) { }

  canActivate(router: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | boolean {


       let token= sessionStorage.getItem('token');
     
       if(token==null){       
         this.router.navigate(['/'])
         return false
       }else{
   
         return true;
       }
    
  }
}