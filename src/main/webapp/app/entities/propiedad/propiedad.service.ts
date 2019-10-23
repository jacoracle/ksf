import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPropiedad } from 'app/shared/model/propiedad.model';

type EntityResponseType = HttpResponse<IPropiedad>;
type EntityArrayResponseType = HttpResponse<IPropiedad[]>;

@Injectable({ providedIn: 'root' })
export class PropiedadService {
  public resourceUrl = SERVER_API_URL + 'api/propiedads';

  constructor(protected http: HttpClient) {}

  create(propiedad: IPropiedad): Observable<EntityResponseType> {
    return this.http.post<IPropiedad>(this.resourceUrl, propiedad, { observe: 'response' });
  }

  update(propiedad: IPropiedad): Observable<EntityResponseType> {
    return this.http.put<IPropiedad>(this.resourceUrl, propiedad, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPropiedad>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPropiedad[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
