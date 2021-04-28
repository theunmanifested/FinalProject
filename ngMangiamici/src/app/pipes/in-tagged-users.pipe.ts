import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'inTaggedUsers'
})
export class InTaggedUsersPipe implements PipeTransform {

  transform(user: string, tagged: string): unknown {

    return tagged.includes(user);
  }

}
