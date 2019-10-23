import { IDireccion } from 'app/shared/model/direccion.model';
import { IDetalle } from 'app/shared/model/detalle.model';

export interface IPropiedad {
  id?: number;
  nombre?: string;
  descripcion?: string;
  tipo?: string;
  imagen?: string;
  geoRef?: string;
  precio?: number;
  direccion?: IDireccion;
  detalle?: IDetalle;
}

export class Propiedad implements IPropiedad {
  constructor(
    public id?: number,
    public nombre?: string,
    public descripcion?: string,
    public tipo?: string,
    public imagen?: string,
    public geoRef?: string,
    public precio?: number,
    public direccion?: IDireccion,
    public detalle?: IDetalle
  ) {}
}
