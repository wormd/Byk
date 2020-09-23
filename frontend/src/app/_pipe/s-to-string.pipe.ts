import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'sToString'
})
export class SToStringPipe implements PipeTransform {

  transform(value: any) {
    if (value >= 2592000) // months
        return Math.round(+value / 2592000* 10)/10 + " months";
    if (value >= 604800) // weeks
        return Math.round(+value / 604800* 10)/10 + " weeks";
    if (value >= 86400) // days
        return Math.round(+value / 86400 * 10)/10 + " days";
    return '';
  }

}
