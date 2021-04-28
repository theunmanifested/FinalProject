import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'inTaggedUsers'
})
export class InTaggedUsersPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
