import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'expired'
})
export class ExpiredPipe implements PipeTransform {

  transform(value) {
    if (value === '') {
      return 'Expired';
    }
    return value;
  }

}
