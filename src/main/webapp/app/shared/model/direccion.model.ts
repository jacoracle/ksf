export interface IDireccion {
  id?: number;
  calle?: string;
  colonia?: string;
  munucipio?: string;
  estado?: string;
  codigoPostl?: number;
}

export class Direccion implements IDireccion {
  constructor(
    public id?: number,
    public calle?: string,
    public colonia?: string,
    public munucipio?: string,
    public estado?: string,
    public codigoPostl?: number
  ) {}
}
