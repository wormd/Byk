import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'toUnixt'
})
export class ToUnixtPipe implements PipeTransform {

  transform(value: any) {
    return new Date(value).getTime() / 1000;
  }

}
