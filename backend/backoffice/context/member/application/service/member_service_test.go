package service

import (
	"errors"
	"github.com/golang/mock/gomock"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/application/port/in"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/application/port/out"
	mockOut "github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/application/port/out/mock"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/domain"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/vo"
	"reflect"
	"testing"
)

func Test_memberService_RegisterMember(t *testing.T) {
	type fields struct {
		saver func(ctrl *gomock.Controller) out.MemberSaver
	}
	type args struct {
		param in.MemberCreateCommand
	}
	tests := []struct {
		name    string
		fields  fields
		args    args
		want    vo.MemberId
		wantErr bool
	}{
		{
			name: "success",
			fields: fields{
				saver: func(ctrl *gomock.Controller) out.MemberSaver {
					m := mockOut.NewMockMemberSaver(ctrl)
					m.EXPECT().
						Save(
							gomock.Eq(&domain.Member{
								Name:        "lee",
								PhoneNumber: "0123456789",
							}),
						).
						Return(vo.MemberId(1), nil)
					return m
				},
			},
			args: args{
				param: in.MemberCreateCommand{
					Name:        "lee",
					PhoneNumber: "0123456789",
				},
			},
			want:    vo.MemberId(1),
			wantErr: false,
		},
		{
			name: "fail",
			fields: fields{
				saver: func(ctrl *gomock.Controller) out.MemberSaver {
					m := mockOut.NewMockMemberSaver(ctrl)
					m.EXPECT().
						Save(
							gomock.Eq(&domain.Member{
								Name:        "lee",
								PhoneNumber: "0123456789",
							}),
						).
						Return(vo.EmptyMemberId, errors.New("error"))
					return m
				},
			},
			args: args{
				param: in.MemberCreateCommand{
					Name:        "lee",
					PhoneNumber: "0123456789",
				},
			},
			want:    vo.EmptyMemberId,
			wantErr: true,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			ctrl := gomock.NewController(t)
			defer ctrl.Finish()

			m := memberService{
				saver: tt.fields.saver(ctrl),
			}
			got, err := m.RegisterMember(tt.args.param)
			if (err != nil) != tt.wantErr {
				t.Errorf("RegisterMember() error = %v, wantErr %v", err, tt.wantErr)
				return
			}
			if got != tt.want {
				t.Errorf("RegisterMember() got = %v, want %v", got, tt.want)
			}
		})
	}
}

func Test_memberService_ReadMember(t *testing.T) {
	type fields struct {
		finder func(ctrl *gomock.Controller) out.MemberFinder
	}
	type args struct {
		id vo.MemberId
	}
	tests := []struct {
		name    string
		fields  fields
		args    args
		want    *in.MemberReadResult
		wantErr bool
	}{
		{
			name: "success",
			fields: fields{
				finder: func(ctrl *gomock.Controller) out.MemberFinder {
					m := mockOut.NewMockMemberFinder(ctrl)
					m.EXPECT().FindById(gomock.Eq(vo.MemberId(1))).Return(&domain.Member{
						Id:          1,
						Name:        "lee",
						PhoneNumber: "0123456789",
					}, nil)
					return m
				},
			},
			args: args{
				id: vo.MemberId(1),
			},
			want: &in.MemberReadResult{
				Id:          1,
				Name:        "lee",
				PhoneNumber: "0123456789",
			},
			wantErr: false,
		},
		{
			name: "fail",
			fields: fields{
				finder: func(ctrl *gomock.Controller) out.MemberFinder {
					m := mockOut.NewMockMemberFinder(ctrl)
					m.EXPECT().FindById(gomock.Eq(vo.MemberId(1))).Return(nil, errors.New("error"))
					return m
				},
			},
			args: args{
				id: vo.MemberId(1),
			},
			want:    nil,
			wantErr: true,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			ctrl := gomock.NewController(t)
			defer ctrl.Finish()

			m := memberService{
				finder: tt.fields.finder(ctrl),
			}
			got, err := m.ReadMember(tt.args.id)
			if (err != nil) != tt.wantErr {
				t.Errorf("ReadMember() error = %v, wantErr %v", err, tt.wantErr)
				return
			}
			if !reflect.DeepEqual(got, tt.want) {
				t.Errorf("ReadMember() got = %v, want %v", got, tt.want)
			}
		})
	}
}

func Test_memberService_UpdateMember(t *testing.T) {
	type fields struct {
		updater func(ctrl *gomock.Controller) out.MemberUpdater
	}
	type args struct {
		id    vo.MemberId
		param in.MemberUpdateCommand
	}
	tests := []struct {
		name    string
		fields  fields
		args    args
		wantErr bool
	}{
		{
			name: "success",
			fields: fields{
				updater: func(ctrl *gomock.Controller) out.MemberUpdater {
					mock := mockOut.NewMockMemberUpdater(ctrl)
					mock.EXPECT().Update(gomock.Eq(vo.MemberId(1)), gomock.Any()).Return(nil)
					return mock
				},
			},
			args: args{
				id:    vo.MemberId(1),
				param: in.MemberUpdateCommand{},
			},
			wantErr: false,
		},
		{
			name: "fail",
			fields: fields{
				updater: func(ctrl *gomock.Controller) out.MemberUpdater {
					mock := mockOut.NewMockMemberUpdater(ctrl)
					mock.EXPECT().Update(gomock.Eq(vo.MemberId(1)), gomock.Any()).Return(errors.New("error"))
					return mock
				},
			},
			args: args{
				id:    vo.MemberId(1),
				param: in.MemberUpdateCommand{},
			},
			wantErr: true,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			ctrl := gomock.NewController(t)
			defer ctrl.Finish()

			m := memberService{
				updater: tt.fields.updater(ctrl),
			}
			if err := m.UpdateMember(tt.args.id, tt.args.param); (err != nil) != tt.wantErr {
				t.Errorf("UpdateMember() error = %v, wantErr %v", err, tt.wantErr)
			}
		})
	}
}

func Test_memberService_DeleteMember(t *testing.T) {
	type fields struct {
		deleter func(ctrl *gomock.Controller) out.MemberDeleter
	}
	type args struct {
		id vo.MemberId
	}
	tests := []struct {
		name    string
		fields  fields
		args    args
		wantErr bool
	}{
		{
			name: "success",
			fields: fields{
				deleter: func(ctrl *gomock.Controller) out.MemberDeleter {
					m := mockOut.NewMockMemberDeleter(ctrl)
					m.EXPECT().Delete(gomock.Eq(vo.MemberId(1))).Return(nil)
					return m
				},
			},
			args: args{
				id: vo.MemberId(1),
			},
			wantErr: false,
		},
		{
			name: "fail",
			fields: fields{
				deleter: func(ctrl *gomock.Controller) out.MemberDeleter {
					m := mockOut.NewMockMemberDeleter(ctrl)
					m.EXPECT().Delete(gomock.Eq(vo.MemberId(1))).Return(errors.New("error"))
					return m
				},
			},
			args: args{
				id: vo.MemberId(1),
			},
			wantErr: true,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			ctrl := gomock.NewController(t)
			defer ctrl.Finish()

			m := memberService{
				deleter: tt.fields.deleter(ctrl),
			}
			if err := m.DeleteMember(tt.args.id); (err != nil) != tt.wantErr {
				t.Errorf("DeleteMember() error = %v, wantErr %v", err, tt.wantErr)
			}
		})
	}
}
