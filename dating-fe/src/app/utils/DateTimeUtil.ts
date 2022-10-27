import {AbstractControl, ValidationErrors} from '@angular/forms';

export function validatorAge(control: AbstractControl): ValidationErrors | null {

  const dateValue = new Date(control.value);

  const dateNow = Math.floor(new Date().getFullYear());

  const dateOfBirth = Math.floor(dateValue.getFullYear());

  if (((dateNow - dateOfBirth) < 16) || ((dateNow - dateOfBirth) > 100)) {
    return {"checkAge": true};
  }
  return null;

  // return (((dateNow - dateOfBirth) < 18) || ((dateNow - dateOfBirth) > 100)) ? {dateOfBirth: true} : null;
}
